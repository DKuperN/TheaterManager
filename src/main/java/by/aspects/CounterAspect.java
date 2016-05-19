package by.aspects;

import by.core.models.AspectsModel;
import by.core.services.impl.AspectServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * count how many times each event was accessed by name, how many times its prices were queried, how many times its tickets were booked
 */
@Component
@Aspect
public class CounterAspect {

    @Autowired
    AspectServiceImpl aspectService;

    public CounterAspect() {}

    /**
     * колличество поисков эвента по имени
     */
    @Pointcut("execution(* by.core.services.impl.EventServiceImpl.getEventByName(..))")
    private void countGetEventByName(){}
    int countGetEventByName = 0;
    @AfterReturning("countGetEventByName()")
    public void countGetEventByNamePointcut(JoinPoint joinPoint) throws ParseException {
        countGetEventByName++;
        Date date = new Date(System.currentTimeMillis());
        DateFormat timeFormatter = new SimpleDateFormat("HH.mm");
        Calendar calendar = new GregorianCalendar();
        String time = timeFormatter.format(calendar.getTime());
        AspectsModel aspectsModel = new AspectsModel("countGetEventByName", countGetEventByName, new java.sql.Date(date.getTime()), new Time(timeFormatter.parse(time).getTime()));
        aspectService.storeAspect(aspectsModel);
        System.out.println("###### Counter: countGetEventByName saved in DB!");
    }

    /**
     * колличество запросов цены билета
     */
    @Pointcut("execution(* by.core.models.EventModel.getPriceForTicket(..))")
    private void countGetPriceForTicket(){}
    int countGetPriceForTicket = 0;
    @AfterReturning("countRegisterUser()")
    public void countGetPriceForTicket(JoinPoint joinPoint) throws ParseException {
        countGetPriceForTicket++;
        Date date = new Date(System.currentTimeMillis());
        DateFormat timeFormatter = new SimpleDateFormat("HH.mm");
        Calendar calendar = new GregorianCalendar();
        String time = timeFormatter.format(calendar.getTime());
        AspectsModel aspectsModel = new AspectsModel("countRegisterUser", countGetPriceForTicket, new java.sql.Date(date.getTime()), new Time(timeFormatter.parse(time).getTime()));
        aspectService.storeAspect(aspectsModel);
        System.out.println("###### Counter: countRegisterUser saved in DB!");
    }

    /**
     * колличество вызова метода регистрации пользователя
     */
    @Pointcut("execution(* *.registerUser(..))")
    private void countRegisterUser(){}
    int countRegisteredUser = 0;
    @AfterReturning("countRegisterUser()")
    public void countRegisterUserPointcut(JoinPoint joinPoint) throws ParseException {
        countRegisteredUser++;
        Date date = new Date(System.currentTimeMillis());
        DateFormat timeFormatter = new SimpleDateFormat("HH.mm");
        Calendar calendar = new GregorianCalendar();
        String time = timeFormatter.format(calendar.getTime());
        AspectsModel aspectsModel = new AspectsModel("countRegisterUser", countRegisteredUser, new java.sql.Date(date.getTime()), new Time(timeFormatter.parse(time).getTime()));
        aspectService.storeAspect(aspectsModel);
        System.out.println("###### Counter: countRegisterUser saved in DB!");
    }

    /**
     * колличество вызовов бронирования билета
     */
    @Pointcut("execution(* *.bookTicket(..))")
    private void countBookTicket(){}
    int countBookTicket = 0;
    @After("countBookTicket()")
    private void countBookTicketPointcut(JoinPoint joinPoint) throws ParseException {
        countBookTicket++;
        Date date = new Date(System.currentTimeMillis());
        DateFormat timeFormatter = new SimpleDateFormat("HH.mm");
        Calendar calendar = new GregorianCalendar();
        String time = timeFormatter.format(calendar.getTime());
        AspectsModel aspectsModel = new AspectsModel("countBookTicket", countBookTicket, new java.sql.Date(date.getTime()), new Time(timeFormatter.parse(time).getTime()));
        aspectService.storeAspect(aspectsModel);
        System.out.println("###### Counter: countBookTicket saved in DB!");
    }
}
