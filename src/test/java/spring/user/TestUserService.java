package spring.user;

import models.UserModel;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class TestUserService extends AbstractTestExecutionListener {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private Utils utils;

    @Value("${test.userName}")
    private String userName;
    @Value("${test.userEmail}")
    private String testEmail;

    //TODO
    //@Ignore
    //@Before
    public void beforeTestClass() throws Exception {
        userService.registerUser(userName, testEmail);
        System.out.println("Created test user!");
    }

    @Ignore
    //@AfterClass
    public void afterTestClass() throws Exception {
        userService.removeUser(userName);
        System.out.println("Test user removed!");
    }

    @Test
    public void testFindUserByName() throws Exception {

        UserModel userModel = userService.getUserById(userService.findUserIdByName(userName));
        assertEquals(userName, userModel.getUserName());
        assertEquals(testEmail, userModel.getUserEmail());
        System.out.println("testFindUserByName:");
        printUserInfo(userModel);
    }

    @Test
    public void testFindUserByEmail() throws Exception {
        UserModel userModel = userService.getUserById(userService.findUserIdByName(testEmail));
        assertEquals(userName, userModel.getUserName());
        assertEquals(testEmail, userModel.getUserEmail());
        System.out.println("testFindUserByEmail:");
        printUserInfo(userModel);
    }

    @Test
    public void testGetUserById() throws Exception {
        assertNotNull(userService.getUserById(userService.findUserIdByName(userName)));
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
        System.out.println("User id:    " + userModel.getUserId());
        System.out.println("User name:  " + userModel.getUserName());
        System.out.println("User Email: " + userModel.getUserEmail());
        System.out.println("***************");
    }
}
