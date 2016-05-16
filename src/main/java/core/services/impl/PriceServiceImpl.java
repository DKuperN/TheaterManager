package core.services.impl;

import core.daos.impl.BookingDAOImpl;
import core.services.PriceService;

public class PriceServiceImpl implements PriceService {

    private BookingDAOImpl bookingDAO;

    public PriceServiceImpl(BookingDAOImpl bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public boolean isPlaceVip(String eventName, int placeNumber) {
        return false;
    }
}
