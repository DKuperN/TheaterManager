package daos.impl;

import daos.UserDAO;
import models.UserModel;

import javax.sql.DataSource;
import java.util.Random;

public class UserDaoImpl implements UserDAO<UserModel> {

    private DataSource dataSource;


    public void saveNewUser(String userName, String userEmail) {
        System.out.println("User " + userName + " with " + userEmail + " WILL be save in DB (not ready schema)");
    }

    public void deleteUser(int userID) {
        System.out.println("Deleted user with random ID: " + userID);
    }

    public UserModel getUserById(int id) {
        return null;
    }

    public UserModel getUserByName(String userName) {
        return null;
    }

    public UserModel getUserByEmail(String userEmail) {
        return null;
    }

    //TODO for test
    public int getUserIdByName(String userName) {
        Random r = new Random();
        return r.nextInt(100);
    }

}
