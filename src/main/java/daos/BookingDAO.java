package daos;

import models.EventModel;
import models.TicketModel;

import java.io.IOException;
import java.util.Map;

public interface BookingDAO {
    Double getBaseTicketPrice(String eventName, String userName);
    TicketModel bookTicket(String eventName, String userName, int placeNumber) throws IOException;
    Map<Integer, Double> getPurchasedTicketsForEvent(String eventName, String auditName);
    boolean isPlaceVip(String event, int placeNumber);
}
