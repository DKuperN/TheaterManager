package spring.auditorium;


import by.annotationbeans.AnnotationBeans;
import by.core.models.AuditoriumModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import by.core.services.impl.AuditoriumServiceImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AnnotationBeans.class})
public class AuditoriumTest {
    @Autowired
    private AuditoriumServiceImpl auditoriumService;

    @Test
    public void getAllAuditTest() throws IOException {
        Map<String, AuditoriumModel> allAuditoriums = auditoriumService.getAllAuditoriums();
        assertNotNull(allAuditoriums);
        System.out.println("getAllAuditTest OK!");
        System.out.println("Auditoruims:");
        for (Map.Entry<String, AuditoriumModel> entry : allAuditoriums.entrySet()) {
            printAuditInfo(entry.getValue());
            System.out.println("--------------------------");
        }
    }

    String auditTestName = "Test theatre";
    int auditTestCapacity = 150;

    @Test
    public void getAuditByName() throws IOException {
        AuditoriumModel auditoriumModel = auditoriumService.getAuditoriumByName(auditTestName);
        assertEquals(auditTestName, auditoriumModel.getAuditoriumName());
        System.out.println("getAuditByName OK!");
        System.out.println("Auditorium *" + auditTestName+ "* founded:");
        printAuditInfo(auditoriumModel);
    }

    @Test
    public void getAuditCapacity() throws IOException {
        AuditoriumModel auditoriumModel = auditoriumService.getAuditoriumByName(auditTestName);
        assertEquals(auditTestCapacity, auditoriumModel.getAuditoriumCapacity());
        System.out.println("getAuditCapacity OK!");
        System.out.println("Auditorium *" + auditTestName+ "* capacity: " + auditoriumModel.getAuditoriumCapacity());
    }
    @Test
    public void getAuditVipSeats() throws IOException {
        AuditoriumModel auditoriumModel = auditoriumService.getAuditoriumByName(auditTestName);
        assertEquals(auditTestName, auditoriumModel.getAuditoriumName());
        System.out.println("getAuditVipSeats OK!");
        System.out.println("Auditorium *" + auditTestName+ "* VIP seats: " + Arrays.toString(auditoriumModel.getAuditoriumVIPSeats()));
    }

    private void printAuditInfo(AuditoriumModel auditoriumModel) {
        System.out.println("Auditorium name:     " + auditoriumModel.getAuditoriumName());
        System.out.println("Auditorium capacity: " + auditoriumModel.getAuditoriumCapacity());
        System.out.println("VIP seats:           " + Arrays.toString(auditoriumModel.getAuditoriumVIPSeats()));
    }

}
