package spring.user;

import by.annotationbeans.AnnotationBeans;
import by.core.models.UserModel;
import by.core.services.impl.UserServiceImpl;
import by.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AnnotationBeans.class})
public class TestUserService extends AbstractTestExecutionListener {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    Environment environment;

    @Test
    public void testFindUserByName() throws Exception {
        String userName = environment.getProperty("test.userName");
        String testEmail = environment.getProperty("test.userEmail");
        UserModel userModel = userService.getUserById(userService.findUserIdByName(userName));
        assertEquals(userName, userModel.getUserName());
        assertEquals(testEmail, userModel.getUserEmail());
        System.out.println("testFindUserByName:");
        printUserInfo(userModel);
    }

    @Test
    public void testFindUserByEmail() throws Exception {
        String userName = environment.getProperty("test.userName");
        String testEmail = environment.getProperty("test.userEmail");
        UserModel userModel = userService.getUserById(userService.findUserIdByName(testEmail));
        assertEquals(userName, userModel.getUserName());
        assertEquals(testEmail, userModel.getUserEmail());
        System.out.println("testFindUserByEmail:");
        printUserInfo(userModel);
    }

    @Test
    public void testGetUserById() throws Exception {
        String userName = environment.getProperty("test.userName");
        String testEmail = environment.getProperty("test.userEmail");
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
        System.out.println("***************");

    }

    private void printUserInfo(UserModel userModel) throws IOException {
        System.out.println("User id:    " + userModel.getUserId());
        System.out.println("User name:  " + userModel.getUserName());
        System.out.println("User Email: " + userModel.getUserEmail());
        System.out.println("***************");
    }
}
