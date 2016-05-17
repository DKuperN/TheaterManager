package spring.event;

import by.annotationbeans.AnnotationBeans;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import by.core.services.impl.EventServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AnnotationBeans.class})
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
