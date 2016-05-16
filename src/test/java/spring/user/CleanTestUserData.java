package spring.user;

import core.daos.impl.UserDaoImpl;
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
public class CleanTestUserData {
    @Autowired
    private UserDaoImpl userDao;
    private String testName = "testUser";

    @Test
    public void cleanUserTestData() throws Exception {
        userDao.deleteUser(userDao.getUserIdByName(testName));
        System.out.println("Test user deleted!");
    }
}
