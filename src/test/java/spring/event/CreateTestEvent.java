package spring.event;

import by.annotationbeans.AnnotationBeans;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import by.core.services.impl.EventServiceImpl;
import by.utils.Utils;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AnnotationBeans.class})
public class CreateTestEvent {

    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private Utils utils;
    @Autowired
    Environment environment;

    @Test
    public void createTestEvent() throws ParseException, IOException {
        String eName = environment.getProperty("test.eventName");
        String ePlace = environment.getProperty("test.eventPlace");
        String seDate = environment.getProperty("test.eventDate");
        String seStartTime = environment.getProperty("test.eventStartTime");
        String seEndTime = environment.getProperty("test.eventEndTime");
        String sePrice = environment.getProperty("test.eventPrice");
        String seRating = environment.getProperty("test.eventRating");

        //TestData
        DateFormat formatPattern = new SimpleDateFormat("yyyy-mm-dd");
        Date eDate = formatPattern.parse(seDate);
        Time eStartTime = utils.timeFormatter(seStartTime);
        Time eEndTime = utils.timeFormatter(seEndTime);
        Double ePrice = Double.parseDouble(sePrice);
        int eRating = Integer.parseInt(seRating);

        eventService.createEvent(eName, ePlace, eDate, eStartTime, eEndTime, ePrice, eRating);
        System.out.println("Created test Event!");
    }

}
