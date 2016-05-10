package spring.user;

import daos.impl.UserDaoImpl;
import models.UserModel;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(locations = {"classpath:springContext.xml"})
})
public class UserDAO extends AbstractTestExecutionListener {

    @Autowired
    private UserDaoImpl userDao;
    private String testName = "testUser";
    private String testEmail = "testUserEmail@email.em";

    //TODO
    @Ignore
    //@BeforeClass
    public void beforeTestClass() throws Exception {
        userDao.saveNewUser(testName, testEmail);
        System.out.println("Created test user!");
    }

    @Ignore
    //@AfterClass
    public void afterTestClass() throws Exception {
        userDao.deleteUser(userDao.getUserIdByName(testName));
        System.out.println("Test user removed!");
    }

    @Test
    public void testFindUserByName() throws Exception {

        UserModel userModel = userDao.getUserById(userDao.getUserIdByName(testName));
        assertEquals(testName, userModel.getUserName());
        assertEquals(testEmail, userModel.getUserEmail());

    }
    @Test
    public void testFindUserByEmail() throws Exception {
        UserModel userModel = userDao.getUserById(userDao.getUserIdByName(testEmail));
        assertEquals(testName, userModel.getUserName());
        assertEquals(testEmail, userModel.getUserEmail());
    }

    @Test
    public void testGetUserById() throws Exception {
        assertNotNull(userDao.getUserById(userDao.getUserIdByName(testName)));
    }

}
