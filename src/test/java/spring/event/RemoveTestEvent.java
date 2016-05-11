package spring.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.impl.EventServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class RemoveTestEvent {

    @Autowired
    private EventServiceImpl eventService;

    String eName = "Dracula";

    @Test
    public void removeTestEvent(){
        eventService.deleteEvent(eName);
        System.out.println("Test event " + eName + " removed!");
    }
}
