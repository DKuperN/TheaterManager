package services;

import models.TicketModel;

import java.sql.Date;
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
     * @param userName
     * @param seatNumber
     */
    TicketModel bookTicket(String eventName, String userName, int seatNumber);

    TicketModel bookTicket(String eventName, String userName, int seatNumber, boolean enableDiscountStrategy);

    /**
     * Get all purchased tickets for event
     * @param auditName
     * @param eventName
     * @return
     */
    Map<String, Object> getPurchasedTicketsForEvent(String eventName, Date eventDate);

}
