package spring;

import consoleui.AppMenu;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.impl.*;
import utils.Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class ContextCreationTestCase extends TestCase {
    @Autowired
    AppMenu appMenu;

    @Autowired
    EventServiceImpl eventService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    BookingServiceImpl bookingService;
    @Autowired
    AuditoriumServiceImpl auditoriumService;
    @Autowired
    PriceServiceImpl priceService;

    @Autowired
    Utils utils;

    @Test
    public void menuBean() throws Exception {
        System.out.println("AppMenuBean = " + appMenu);
        assertNotNull(appMenu);
        System.out.println("AppMenu bean is OK!");
    }
    @Test
    public void creationServices() throws Exception {
        System.out.println("EventSeviceBean = " + eventService);
        assertNotNull(eventService);
        System.out.println("UserSeviceBean = " + userService);
        assertNotNull(userService);
        System.out.println("BookingServiceBean = " + bookingService);
        assertNotNull(bookingService);
        System.out.println("AuditoriumServiceBean = " + auditoriumService);
        assertNotNull(auditoriumService);
        System.out.println("PriceServiceBean = " + priceService);
        assertNotNull(priceService);
        System.out.println("All service beans are OK!");
    }
    @Test
    public void creationUtils() throws Exception {
        System.out.println("UtilsBean = " + utils);
        assertNotNull(utils);
        System.out.println("Util bean is OK!");
    }


}
