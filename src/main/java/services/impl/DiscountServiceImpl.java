package services.impl;

import services.DiscountService;
import strategy.DiscountServiceStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DiscountServiceImpl implements DiscountService {

    private ArrayList<DiscountServiceStrategy> serviceStrategy;

    public DiscountServiceImpl(ArrayList<DiscountServiceStrategy> serviceStrategy) {
        this.serviceStrategy = serviceStrategy;
    }

    @Override
    public int getDiscount(String userName, String eventName, Date dateTime, int ticketId) {
        int[] discountArr = new int[serviceStrategy.size()];
        int count = 0;
        for(DiscountServiceStrategy discount : serviceStrategy){
            discountArr[count] = discount.getDiscount(userName, eventName, dateTime, ticketId);
            count ++;
        }
        Arrays.sort(discountArr);
        return discountArr[discountArr.length-1];
    }
}
