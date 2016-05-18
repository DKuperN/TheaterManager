package by.aspects;

import by.core.models.AspectsModel;
import by.core.services.impl.AspectServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void countGetEventByNamePointcut(JoinPoint joinPoint){
        countGetEventByName++;
        System.out.println("###### Event was accessed by name : " + countGetEventByName + " times!");
    }

    /**
     * колличество запросов цены билета
     */
    @Pointcut("execution(* by.core.models.EventModel.getPriceForTicket(..))")
    private void countGetPriceForTicket(){}
    int countGetPriceForTicket = 0;
    @AfterReturning("countRegisterUser()")
    public void countGetPriceForTicket(JoinPoint joinPoint) {
        countGetPriceForTicket++;
        System.out.println("###### Ticket price was interested: " + countGetPriceForTicket + " times!");
    }

    /**
     * колличество вызова метода регистрации пользователя
     */
    @Pointcut("execution(* *.registerUser(..))")
    private void countRegisterUser(){}
    int countRegisteredUser = 0;
    @AfterReturning("countRegisterUser()")
    public void countRegisterUserPointcut(JoinPoint joinPoint){
        countRegisteredUser++;
        System.out.println("###### Total user(s) : " + countRegisteredUser + " was registered");
    }

    /**
     * колличество вызовов бронирования билета
     */
    @Pointcut("execution(* *.bookTicket(..))")
    private void countBookTicket(){}
    int countBookTicket = 0;
    @After("countBookTicket()")
    private void countBookTicketPointcut(){
        countBookTicket++;
        // TODO: 18-May-16  сдалать в таблице еще один ключ с именем аспект каунта, и по нему потом делать апдейт
//        AspectsModel aspectsModel = new AspectsModel();
//        aspectService.storeAspect(aspectsModel);
        System.out.println("###### Ticket was booked: " + countBookTicket + " times!");
    }
}
