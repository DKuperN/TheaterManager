package services.impl;

import daos.impl.UserDaoImpl;
import models.UserModel;
import services.UserService;

public class UserServiceImpl implements UserService {

    private UserDaoImpl userDao;

    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public void registerUser(String userName, String email) {
        userDao.saveNewUser(userName, email);
    }

    public void removeUser(String userName) {
        userDao.deleteUser(findUserIdByName(userName));
    }

    public UserModel getUserByEmail(String userEmail) {
        return null;
    }

    public UserModel getUserByName(String userName) {
        return null;
    }

    public UserModel getUserById(int userId) {
        return null;
    }

    public int findUserIdByName(String userName) {
        return userDao.getUserIdByName(userName);
    }
}
