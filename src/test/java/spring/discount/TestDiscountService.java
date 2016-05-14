package spring.discount;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.impl.DiscountServiceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:discountStrategy.xml"})
public class TestDiscountService {

    @Autowired
    DiscountServiceImpl discountService;

    private String userName = "newUser";
    private String eventName = "test";
    private String seDate = "2015-01-06";
    private Date eDate;
    private String ticketId = "1";

    @Test
    public void TestLoadDiscountServiceBean() throws Exception {
        System.out.println("DiscountService = " + discountService);
        assertNotNull(discountService);
        System.out.println("DiscountService bean is OK!");
    }

    @Test
    public void TestGetDiscount() throws Exception {

        DateFormat formatPattern = new SimpleDateFormat("yyyy-mm-dd");
        Date eDate = formatPattern.parse(seDate);

        int discount = discountService.getDiscount(userName, eventName, eDate);
        assertNotNull(discount);
        System.out.println("Discount - " + discount + "%");
    }


}
