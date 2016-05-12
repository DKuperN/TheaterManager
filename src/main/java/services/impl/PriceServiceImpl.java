package services.impl;

import daos.impl.BookingDAOImpl;
import services.PriceService;

public class PriceServiceImpl implements PriceService {

    private BookingDAOImpl bookingDAO;

    public PriceServiceImpl(BookingDAOImpl bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public boolean isPlaceVip(String eventName, int placeNumber) {
        return false;
    }
}
