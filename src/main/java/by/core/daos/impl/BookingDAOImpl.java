package by.core.daos.impl;

import by.core.daos.BookingDAO;
import by.core.models.BookingModel;
import by.core.models.EventModel;
import by.core.models.TicketModel;
import by.core.models.UserModel;
import by.utils.Utils;

import javax.sql.DataSource;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.jdbc.support.JdbcUtils.closeConnection;

public class BookingDAOImpl implements BookingDAO {

    private DataSource dataSource;
    private Utils utils;
    private BookingModel bookingModel;
    private EventModel event;
    private UserModel user;

    public BookingDAOImpl(DataSource dataSource, Utils utils) {
        this.dataSource = dataSource;
        this.utils = utils;
    }

    public BookingDAOImpl(BookingModel bookingModel, DataSource dataSource, Utils utils, boolean useDiscountStrategy) {
        this.bookingModel = bookingModel;
        this.dataSource = dataSource;
        this.utils = utils;
        this.useDiscountStrategy = useDiscountStrategy;
    }

    public Double getBaseTicketPrice(String eventName, String userName) {
        event = bookingModel.getEventModel();
        user = bookingModel.getUserModel();
        if(event != null && user != null){
            return event.getBasePriceForTicket();
        } else {
            return null;
        }
    }

    private boolean useDiscountStrategy = false;

    public TicketModel bookTicket(BookingModel bookingModel, int placeNumber, boolean useDiscountStrategy) throws IOException {
        this.useDiscountStrategy = useDiscountStrategy;
        return bookTicket(bookingModel, placeNumber);
    }

    public TicketModel bookTicket(BookingModel bookingModel, int placeNumber) throws IOException {
        event = bookingModel.getEventModel();
        user = bookingModel.getUserModel();
        TicketModel ticketModel = null;
        Double resultPrice = utils.getResultPrice(event.getBasePriceForTicket(), bookingModel.isSeatVip(), event.getRating());
        int ticketId = 0;
        int discount = 0;
        if(useDiscountStrategy) {
            discount = bookingModel.getDiscount();
            resultPrice = resultPrice - resultPrice/100*discount;
        }

        if(event != null && user != null) {
            String SQL_SAVE_TICKET_ORDER = "INSERT INTO BOOKTICKETS (userID, eventID, placeNumber, resultPrice, discount) VALUES (?, ?, ?, ?, ?);";
            try {
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SAVE_TICKET_ORDER, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, user.getUserId());
                statement.setInt(2, event.getEventId());
                statement.setInt(3, placeNumber);
                statement.setDouble(4, roundPrice(resultPrice));
                statement.setInt(5, discount);

                int affectedRows = statement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating ticket order failed, no rows affected.");
                }
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        ticketId = generatedKeys.getInt(1);
                    }
                    else {
                        throw new SQLException("Creating  ticket order failed, no ID obtained.");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return ticketModel = new TicketModel(ticketId, event, roundPrice(resultPrice), bookingModel.isSeatVip(), discount);

        } else {
            return null;
        }

    }

    private double roundPrice(Double resultPrice) {
        return new BigDecimal(resultPrice).setScale(2, RoundingMode.UP).doubleValue();
    }

    public Map<String, Object> getPurchasedTicketsForEvent(String eventName, Date eventDate) {
        Map<String, Object> map = null;

        String GET_PURCHASED_TICKETS_FOR_EVENT =
                "SELECT DISTINCT b.resultPrice, b.placeNumber, b.eventID FROM booktickets b, event e " +
                        "WHERE b.eventID = (SELECT et.eventID FROM event et WHERE et.eventName = ? AND et.eventDate = ?)";

        String purchasedTickets = "purchasedTickets";

        String totalSumm = "totalSumm";
        double tSumm = 0;

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_PURCHASED_TICKETS_FOR_EVENT);
            ps.setString(1, eventName);
            ps.setDate(2, eventDate);
            ResultSet rs = ps.executeQuery();

            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch(SQLException ex) {
                System.out.println("Problem with counting rows: " + ex);
            }
            int[] purchasedTicketsArr = new int[size];
            int count = 0;
            while (rs.next()){
                purchasedTicketsArr[count] = rs.getInt("PLACENUMBER");
                double d = rs.getDouble("RESULTPRICE");
                tSumm += d;
                count ++;
            }
            ps.close();
            if(size > 0) {
                map = new HashMap<>();
                map.put(totalSumm, tSumm);
                map.put(purchasedTickets, purchasedTicketsArr);
            }
            return map;
        } catch (SQLException e) {
            System.out.println("FAIL: something wrong with connection or query");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return map;
    }
}
