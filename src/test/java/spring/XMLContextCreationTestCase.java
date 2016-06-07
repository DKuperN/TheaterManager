//package spring;
//
//import by.ui.consoleui.AppMenu;
//import junit.framework.TestCase;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import by.core.services.impl.*;
//import by.utils.Utils;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:springXMLContext.xml"})
//public class XMLContextCreationTestCase extends TestCase {
//    @Autowired
//    AppMenu appMenu;
//
//    @Autowired
//    EventServiceImpl eventService;
//    @Autowired
//    UserServiceImpl userService;
//    @Autowired
//    BookingServiceImpl bookingService;
//    @Autowired
//    AuditoriumServiceImpl auditoriumService;
//
//    @Autowired
//    Utils utils;
//
//    @Test
//    public void menuBean() throws Exception {
//        System.out.println("AppMenuBean = " + appMenu);
//        assertNotNull(appMenu);
//        System.out.println("AppMenu bean is OK!");
//    }
//    @Test
//    public void creationServices() throws Exception {
//        System.out.println("EventSeviceBean = " + eventService);
//        assertNotNull(eventService);
//        System.out.println("UserSeviceBean = " + userService);
//        assertNotNull(userService);
//        System.out.println("BookingServiceBean = " + bookingService);
//        assertNotNull(bookingService);
//        System.out.println("AuditoriumServiceBean = " + auditoriumService);
//        assertNotNull(auditoriumService);
//        System.out.println("All service beans are OK!");
//    }
//    @Test
//    public void creationUtils() throws Exception {
//        System.out.println("UtilsBean = " + utils);
//        assertNotNull(utils);
//        System.out.println("Util bean is OK!");
//    }
//
//
//}
