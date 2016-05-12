package services;

import models.AuditoriumModel;
import models.EventModel;
import models.UserModel;

import java.util.Map;

public interface BookingService {

    /**
     * Returns total price for buying all tickets for specified event on specific date and time for specified seats.
     * @param eventName
     * @param userName
     * @param seatNumber
     * @return
     */
    Double getTicketPrice(String eventName, String userName, int seatNumber);

    /**
     * Book ticket on event for user
     * @param eventName
     * @param seatNumber
     * @param userName
     */
    void bookTicket(String eventName, int seatNumber, String userName);

    /**
     * Get all purchased tickets for event
     * @param eventName
     * @param auditName
     * @return
     */
    Map<Integer, Double> getPurchasedTicketsForEvent(String eventName, String auditName);

}
