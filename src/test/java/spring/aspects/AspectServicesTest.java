package spring.aspects;

import by.annotationbeans.AnnotationBeans;
import by.core.models.AspectsModel;
import by.core.services.impl.AspectServiceImpl;
import by.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AnnotationBeans.class})
public class AspectServicesTest {

    @Autowired
    AspectServiceImpl aspectService;
    @Autowired
    private Utils utils;
    @Autowired
    Environment environment;

    @Test
    public void storeAspectTest() throws ParseException {
        DateFormat formatTimePattern = new SimpleDateFormat("HH.mm");
        AspectsModel aspectsModel = new AspectsModel(
                environment.getProperty("test.aspect.counterName"),
                Integer.parseInt(environment.getProperty("test.aspect.counterQuantity")),
                new Date(),
                new Time(formatTimePattern.parse(environment.getProperty("test.aspect.counterTime")).getTime())
        );
        aspectService.storeAspect(aspectsModel);
    }

    @Test
    public void getAspectByNameTest() throws ParseException {
        String counterName = environment.getProperty("test.aspect.counterName");
        String counterTime = environment.getProperty("test.aspect.counterTime");
        AspectsModel aspectsModel = aspectService.getAspectByName(counterName, counterTime);
        printAspect(aspectsModel);
    }
    @Test
    public void getAllAspectCounters() throws ParseException {
        List<AspectsModel> aspectsModelList = aspectService.getAllAspects();
        for (AspectsModel model : aspectsModelList) {
            printAspect(model);
        }
    }

    private void printAspect(AspectsModel aspectsModel) {
        System.out.println("Searching Aspect-Counter: ");
        System.out.println("Counter ID        : " + aspectsModel.getAspectID());
        System.out.println("Counter Name      : " + aspectsModel.getCounterName());
        System.out.println("Counter quantity  : " + aspectsModel.getCounterQuantity());
        System.out.println("Counter date      : " + aspectsModel.getCounterDateTime());
        System.out.println("Counter timeStart : " + aspectsModel.getCounterStartTime());
        System.out.println("********************");
    }


}
