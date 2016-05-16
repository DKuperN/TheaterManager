package by.annotationbeans;

import by.core.daos.impl.BookingDAOImpl;
import by.core.daos.impl.EventDaoImpl;
import by.core.daos.impl.UserDaoImpl;
import by.core.services.impl.*;
import by.core.strategy.DiscountServiceStrategy;
import by.core.strategy.impl.BirthdayDiscountStrategyImpl;
import by.core.strategy.impl.EveryXTicketHaveYDiscountStrategyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import by.utils.Utils;

import java.util.ArrayList;
import java.util.Date;


@Configuration
@Component
@ComponentScan(basePackages = {"by.core", "by.utils"})
@PropertySource({"classpath:app.properties"})
public class AnnotationBeans {

    @Autowired
    private Environment environment;

    @Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public Utils utils(){
        return new Utils(dataSource());
    }

    //Services
    @Bean
    public AuditoriumServiceImpl auditoriumService(){
        return new AuditoriumServiceImpl(utils());
    }
    @Bean
    public BookingServiceImpl bookingService(){
        return new BookingServiceImpl(bookingDAO(), utils());
    }
    @Bean
    public BirthdayDiscountStrategyImpl birthdayDiscountStrategy() {
        return new BirthdayDiscountStrategyImpl();
    }
    @Bean
    public EveryXTicketHaveYDiscountStrategyImpl everyXTicketHaveYDiscountStrategy() {
        return new EveryXTicketHaveYDiscountStrategyImpl();
    }
//    @Bean
//    public DiscountServiceImpl discountService(){
//        ArrayList<DiscountServiceStrategy> serviceStrategy = new ArrayList<>();
//        serviceStrategy.add(1, birthdayDiscountStrategy());
//        serviceStrategy.add(2, everyXTicketHaveYDiscountStrategy());
//        return new DiscountServiceImpl(serviceStrategy, true, true);
//    }
    @Bean
    public EventServiceImpl eventService(){
        return new EventServiceImpl(eventDao(), new Date(), java.text.DateFormat.getDateTimeInstance());
    }
    @Bean
    public UserServiceImpl userService(){
        return new UserServiceImpl(userDao());
    }

    //DAO
    @Bean
    public BookingDAOImpl bookingDAO(){
        return new BookingDAOImpl();
    }
    @Bean
    public EventDaoImpl eventDao(){
        return new EventDaoImpl(dataSource(), utils());
    }
    @Bean
    public UserDaoImpl userDao(){
        return new UserDaoImpl(dataSource(), utils());
    }

}
