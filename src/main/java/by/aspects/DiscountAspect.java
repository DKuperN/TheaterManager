package by.aspects;

import by.core.models.TicketModel;
import by.core.models.UserModel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * count how many times each discount was given total and for specific user
 */

@Component
@Aspect
public class DiscountAspect {

    public DiscountAspect() {}

    private Map<Integer, Integer> counter;

    @Pointcut("execution(* by.core.services.impl.BookingServiceImpl.bookTicketModel(..))")
    private void countTotalDiscountTime(){}
    @AfterReturning(
            pointcut = "countTotalDiscountTime()",
            returning = "retVal")
    public void countTotalDiscountTimePointcut(Object retVal){
        TicketModel ticketModel = (TicketModel) retVal;
        if (((TicketModel) retVal).getDiscount() > 0){
            counter = new HashMap<>();
            counter.put(((TicketModel) retVal).getTicketId(), ((TicketModel) retVal).getDiscount());
        }

        System.out.println("###### Discount was implement: " + counter.size() + " times!");
    }

}
