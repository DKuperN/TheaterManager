package spring.user;

import daos.impl.UserDaoImpl;
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
@ContextHierarchy({
        @ContextConfiguration(locations = {"classpath:springContext.xml"})
})
public class CreateTestUser {
    @Autowired
    private UserDaoImpl userDao;
    private String testName = "testUser";
    private String testEmail = "testUserEmail@email.em";

    @Test
    public void createNewUser() throws Exception {
        userDao.saveNewUser(testName, testEmail);
        System.out.println("Created test user!");
    }
}
