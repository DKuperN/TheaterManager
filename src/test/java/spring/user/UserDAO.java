package spring.user;

import models.UserModel;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import services.impl.UserServiceImpl;
import utils.Utils;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(locations = {"classpath:springContext.xml"})
})
public class UserDAO extends AbstractTestExecutionListener {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private Utils utils;

    private String testName = "testUser";
    private String testEmail = "testUserEmail@email.em";

    //TODO
    //@Ignore
    //@Before
    public void beforeTestClass() throws Exception {
        userService.registerUser(testName, testEmail);
        System.out.println("Created test user!");
    }

    @Ignore
    //@AfterClass
    public void afterTestClass() throws Exception {
        userService.removeUser(testName);
        System.out.println("Test user removed!");
    }

    @Test
    public void testFindUserByName() throws Exception {

        UserModel userModel = userService.getUserById(userService.findUserIdByName(testName));
        assertEquals(testName, userModel.getUserName());
        assertEquals(testEmail, userModel.getUserEmail());
        System.out.println("testFindUserByName:");
        printUserInfo(userModel);
    }

    @Test
    public void testFindUserByEmail() throws Exception {
        UserModel userModel = userService.getUserById(userService.findUserIdByName(testEmail));
        assertEquals(testName, userModel.getUserName());
        assertEquals(testEmail, userModel.getUserEmail());
        System.out.println("testFindUserByEmail:");
        printUserInfo(userModel);
    }

    @Test
    public void testGetUserById() throws Exception {
        assertNotNull(userService.getUserById(userService.findUserIdByName(testName)));
        System.out.println("test User founded!");
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<UserModel> users = userService.getAllUsers();
        assertNotNull(users);
        System.out.println("All users:");
        for (UserModel model: users){
            printUserInfo(model);
        }
        System.out.println(utils.getPropertyByName("menu.asterix"));

    }

    private void printUserInfo(UserModel userModel) throws IOException {
        System.out.println(utils.getPropertyByName("menu.user.info.userid") + " " + userModel.getUserId());
        System.out.println(utils.getPropertyByName("menu.user.info.Name") + " " + userModel.getUserName());
        System.out.println(utils.getPropertyByName("menu.user.info.Email") + " " + userModel.getUserEmail());
        System.out.println(utils.getPropertyByName("menu.asterix"));
    }
}
