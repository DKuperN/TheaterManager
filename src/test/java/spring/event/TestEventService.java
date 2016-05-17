package spring.event;

import by.annotationbeans.AnnotationBeans;
import by.core.models.EventModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import by.core.services.impl.EventServiceImpl;
import by.utils.Utils;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AnnotationBeans.class})
public class TestEventService {

    @Autowired
    private EventServiceImpl eventService;
    @Autowired
    private Utils utils;
    @Autowired
    Environment environment;

    @Test
    public void testGetEventByName() throws IOException {
        String eName = environment.getProperty("test.eventName");

        EventModel eventModel = eventService.getEventByName(eName);
        assertEquals(eName, eventModel.getEventName());
        System.out.println("TestGetEventByName OK:");
        printEventInfo(eventModel);
    }

    @Test
    public void testGetAllEvents(){
        List<EventModel> eventModelList = eventService.getAllEvents();
        assertNotNull(eventModelList);
        System.out.println("All events:");
        for (EventModel event : eventModelList) {
            printEventInfo(event);
        }
        System.out.println("**************");
    }

    private void printEventInfo(EventModel event) {
        System.out.println("Event ID:     " + event.getEventId());
        System.out.println("Event name:   " + event.getEventName());
        System.out.println("Event date:   " + event.getEventDate());
        System.out.println("Begin:        " + event.getEventStartTime());
        System.out.println("Finish:       " + event.getEventEndTime());
        System.out.println("Ticket price: " + event.getEventName());
        System.out.println("Rating:       " + event.getEventName());
        System.out.println("*************");
    }

}
