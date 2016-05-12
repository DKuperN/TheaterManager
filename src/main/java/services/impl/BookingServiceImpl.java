package services.impl;

import daos.impl.BookingDAOImpl;
import services.BookingService;
import utils.Utils;

import java.util.Map;

public class BookingServiceImpl implements BookingService {

    private BookingDAOImpl booking;
    private Utils utils;

    public BookingServiceImpl(BookingDAOImpl booking, Utils utils) {
        this.booking = booking;
        this.utils = utils;
    }

    public Double getTicketPrice(String eventName, String userName, int seatNumber) {
        return null;
    }

    public void bookTicket(String eventName, int seatNumber, String userName) {

    }

    public Map<Integer, Double> getPurchasedTicketsForEvent(String eventName, String auditName) {
        return null;
    }
}
