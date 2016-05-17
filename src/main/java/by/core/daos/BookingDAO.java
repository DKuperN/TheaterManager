package by.core.daos;

import by.core.models.BookingModel;
import by.core.models.TicketModel;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;

public interface BookingDAO {
    Double getBaseTicketPrice(String eventName, String userName);
    TicketModel bookTicket(BookingModel bookingModel, int placeNumber) throws IOException;
    TicketModel bookTicket(BookingModel bookingModel, int placeNumber, boolean useDiscount) throws IOException;
    Map<String, Object> getPurchasedTicketsForEvent(String eventName, Date eventDate);
}
