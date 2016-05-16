package core.daos;

import core.models.TicketModel;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;

public interface BookingDAO {
    Double getBaseTicketPrice(String eventName, String userName);
    TicketModel bookTicket(String eventName, String userName, int placeNumber) throws IOException;
    TicketModel bookTicket(String eventName, String userName, int placeNumber, boolean useDiscount) throws IOException;
    Map<String, Object> getPurchasedTicketsForEvent(String eventName, Date eventDate);
    boolean isPlaceVip(String event, int placeNumber) throws IOException;
}
