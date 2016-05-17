package spring.user;

import by.annotationbeans.AnnotationBeans;
import by.core.daos.impl.UserDaoImpl;
import by.core.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Denis on 05.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AnnotationBeans.class})
public class CreateTestUser {
    @Autowired
    private UserService userService;
    private String testName = "testUser";
    private String testEmail = "testUserEmail@email.em";

    @Test
    public void createNewUser() throws Exception {
        userService.registerUser(testName, testEmail);
        System.out.println("Created test user!");
    }
}
