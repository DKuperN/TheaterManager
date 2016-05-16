package by.core.services;

import java.io.IOException;
import java.util.Date;

public interface DiscountService {
    int getDiscount(String userName, String eventName, Date dateTime) throws IOException;
}
