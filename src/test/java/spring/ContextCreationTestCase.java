package spring;

import consoleui.AppMenu;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.impl.EventServiceImpl;
import services.impl.UserServiceImpl;
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
    Utils utils;

    @Test
    public void menuBean() throws Exception {
        System.out.println("AppMenuBean = " + appMenu);
        assertNotNull(appMenu);
    }
    @Test
    public void creationServices() throws Exception {
        System.out.println("EventSeviceBean = " + eventService);
        assertNotNull(eventService);
        System.out.println("UserSeviceBean = " + userService);
        assertNotNull(userService);
    }
    @Test
    public void creationUtils() throws Exception {
        System.out.println("UtilsBean = " + utils);
        assertNotNull(utils);
    }


}
