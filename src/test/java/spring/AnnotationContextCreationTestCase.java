package spring;

import by.annotationbeans.AnnotationBeans;
import by.core.services.impl.AuditoriumServiceImpl;
import by.core.services.impl.BookingServiceImpl;
import by.core.services.impl.EventServiceImpl;
import by.core.services.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import by.utils.Utils;

import static junit.framework.TestCase.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AnnotationBeans.class})

public class AnnotationContextCreationTestCase {
    @Autowired
    private EventServiceImpl eventService;
    @Autowired
    private UserServiceImpl userService;
//    @Autowired
//    private BookingServiceImpl bookingService;
    @Autowired
    private AuditoriumServiceImpl auditoriumService;

    @Autowired
    private Utils utils;

    @Test
    public void creationServices() throws Exception {
        System.out.println("EventServiceBean = " + eventService);
        assertNotNull(eventService);
        System.out.println("UserServiceBean = " + userService);
        assertNotNull(userService);
//        System.out.println("BookingServiceBean = " + bookingService);
//        assertNotNull(bookingService);
//        System.out.println("AuditoriumServiceBean = " + auditoriumService);
        assertNotNull(auditoriumService);
        System.out.println("All service beans are OK!");
    }
    @Test
    public void creationUtils() throws Exception {
        System.out.println("UtilsBean = " + utils);
        assertNotNull(utils);
        System.out.println("Util bean is OK!");
    }
}
