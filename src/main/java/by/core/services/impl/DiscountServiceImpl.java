package by.core.services.impl;

import by.core.services.DiscountService;
import by.core.strategy.DiscountServiceStrategy;
import by.core.strategy.impl.BirthdayDiscountStrategyImpl;
import by.core.strategy.impl.EveryXTicketHaveYDiscountStrategyImpl;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Service
public class DiscountServiceImpl implements DiscountService {

    private ArrayList<DiscountServiceStrategy> serviceStrategy;
    private boolean isBirthdayDiscount;
    private boolean isTicketDiscount;

    public DiscountServiceImpl() {}

    public DiscountServiceImpl(ArrayList<DiscountServiceStrategy> serviceStrategy, boolean isBirthdayDiscount, boolean isTicketDiscount) {
        this.serviceStrategy = serviceStrategy;
        this.isBirthdayDiscount = isBirthdayDiscount;
        this.isTicketDiscount = isTicketDiscount;
    }

    @Override
    public int getDiscount(String userName, String eventName, Date dateTime) throws IOException {
        int[] discountArr = new int[serviceStrategy.size()];
        int count = 0;

        for(DiscountServiceStrategy discount : serviceStrategy){
            //TODO в будущем надо будет сделать красиво
            if (discount instanceof BirthdayDiscountStrategyImpl && isBirthdayDiscount){
                discountArr[count] = discount.getDiscount(userName, eventName, dateTime);
            } else if (discount instanceof EveryXTicketHaveYDiscountStrategyImpl && isTicketDiscount){
                discountArr[count] = discount.getDiscount(userName, eventName, dateTime);
            }

            count ++;
        }
        Arrays.sort(discountArr);
        return discountArr[discountArr.length-1];
    }
}
