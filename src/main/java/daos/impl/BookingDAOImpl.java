package daos.impl;

import daos.BookingDAO;
import models.EventModel;
import models.TicketModel;
import models.UserModel;
import services.impl.AuditoriumServiceImpl;
import services.impl.BookingServiceImpl;
import services.impl.EventServiceImpl;
import services.impl.UserServiceImpl;
import utils.Utils;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BookingDAOImpl implements BookingDAO {

    private DataSource dataSource;
    private UserServiceImpl userService;
    private EventServiceImpl eventService;
    private AuditoriumServiceImpl auditoriumService;
    private Utils utils;

    public BookingDAOImpl(DataSource dataSource, Utils utils) {
        this.dataSource = dataSource;
        this.utils = utils;
    }

    public BookingDAOImpl(DataSource dataSource, UserServiceImpl userService, EventServiceImpl eventService, AuditoriumServiceImpl auditoriumService, Utils utils) {
        this.dataSource = dataSource;
        this.userService = userService;
        this.eventService = eventService;
        this.auditoriumService = auditoriumService;
        this.utils = utils;
    }

    private EventModel event;
    private UserModel user;

    public Double getBaseTicketPrice(String eventName, String userName) {
        event = eventService.getEventByName(eventName);
        user = userService.getUserByName(userName);
        if(event != null && user != null){
            return event.getBasePriceForTicket();
        } else {
            return null;
        }
    }

    public TicketModel bookTicket(String eventName, String userName, int placeNumber) throws IOException {
        event = eventService.getEventByName(eventName);
        user = userService.getUserByName(userName);
        TicketModel ticketModel = null;
        Double resultPrice = utils.getResultPrice(event.getBasePriceForTicket(), isPlaceVip(event.getEventName(), placeNumber));
        int ticketId = Integer.parseInt(null);

        if(event != null && user != null) {
            String SQL_SAVE_TICKET_ORDER = "INSERT INTO BOOKTICKETS (userID, eventID, placeNumber, resultPrice) VALUES (?, ?, ?, ?);";
            try {
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SAVE_TICKET_ORDER, Statement.RETURN_GENERATED_KEYS);
//                HashMap<Integer, Number> map = new HashMap<Integer, Number>();
                statement.setInt(1, user.getUserId());
                statement.setInt(2, event.getEventId());
                statement.setInt(3, placeNumber);
                statement.setDouble(4, resultPrice);

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

//            utils.executeQuery(SQL_SAVE_TICKET_ORDER, map);


            return ticketModel = new TicketModel(ticketId, event, resultPrice);

        } else {
            return null;
        }

    }

    public Map<Integer, Double> getPurchasedTicketsForEvent(String eventName, String auditName) {
        return null;
    }

    public boolean isPlaceVip(String eName, int placeNumber) {
        int[] vipSeats = auditoriumService.getVipSeats(eName);
        for (int n : vipSeats) {
            if (placeNumber == n) {
                return true;
            }
        }
        return false;
    }
}
