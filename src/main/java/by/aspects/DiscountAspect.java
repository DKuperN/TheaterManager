package by.aspects;

import by.core.models.AspectsModel;
import by.core.models.TicketModel;
import by.core.models.UserModel;
import by.core.services.impl.AspectServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * count how many times each discount was given total and for specific user
 */

@Component
@Aspect
public class DiscountAspect {

    @Autowired
    AspectServiceImpl aspectService;

    public DiscountAspect() {}

    private Map<Integer, Integer> counter;

    //TODO для подсчета скидок конкретному пользоваетля будет использоваться тот же поинткат, только на вывод результата надо будет проверку по userID добавить

    @Pointcut("execution(* by.core.services.impl.BookingServiceImpl.bookTicketModel(..))")
    private void countTotalDiscountTime(){}
    @AfterReturning(
            pointcut = "countTotalDiscountTime()",
            returning = "retVal")
    public void countTotalDiscountTimePointcut(Object retVal) throws ParseException {
        TicketModel ticketModel = (TicketModel) retVal;
        if (((TicketModel) retVal).getDiscount() > 0){
            counter = new HashMap<>();
            counter.put(((TicketModel) retVal).getTicketId(), ((TicketModel) retVal).getDiscount());
        }

        Date date = new Date(System.currentTimeMillis());
        DateFormat timeFormatter = new SimpleDateFormat("HH.mm");
        Calendar calendar = new GregorianCalendar();
        String time = timeFormatter.format(calendar.getTime());
        AspectsModel aspectsModel = new AspectsModel("countTotalDiscountTime", counter.size(), new java.sql.Date(date.getTime()), new Time(timeFormatter.parse(time).getTime()));
        aspectService.storeAspect(aspectsModel);
        System.out.println("###### Counter: countTotalDiscountTime saved in DB!");
    }

}
