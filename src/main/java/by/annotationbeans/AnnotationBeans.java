package by.annotationbeans;

import by.aspects.CounterAspect;
import by.aspects.DiscountAspect;
import by.core.daos.impl.BookingDAOImpl;
import by.core.daos.impl.EventDaoImpl;
import by.core.daos.impl.UserDaoImpl;
import by.core.services.impl.AuditoriumServiceImpl;
import by.core.services.impl.BookingServiceImpl;
import by.core.services.impl.EventServiceImpl;
import by.core.services.impl.UserServiceImpl;
import by.utils.Utils;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.util.Date;


@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
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
        return new BookingDAOImpl(dataSource(), utils());
    }
    @Bean
    public EventDaoImpl eventDao(){
        return new EventDaoImpl(dataSource(), utils());
    }
    @Bean
    public UserDaoImpl userDao(){
        return new UserDaoImpl(dataSource(), utils());
    }

    //Aspects
    @Bean
    public CounterAspect counterAspect() {
        return new CounterAspect();
    }
    @Bean
    public DiscountAspect discountAspect() {
        return new DiscountAspect();
    }

}
