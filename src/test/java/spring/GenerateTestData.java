package spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.event.CreateTestEvent;
import spring.user.CreateTestUser;

import java.io.IOException;
import java.text.ParseException;

/**
 * Generate TestData
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(locations = {"classpath:springContext.xml"}),
        @ContextConfiguration(locations = {"classpath:testContext.xml"})
})
public class GenerateTestData {
    @Autowired
    CreateTestEvent createTestEvent;
    @Autowired
    CreateTestUser createTestUser;

    @Test
    public void generateTestData() throws Exception {
        createTestUser.createNewUser();
        createTestEvent.createTestEvent();
    }
}
