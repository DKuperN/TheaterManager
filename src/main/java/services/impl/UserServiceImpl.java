package services.impl;

import daos.impl.UserDaoImpl;
import models.UserModel;
import services.UserService;

import java.util.List;

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

    public UserModel getUserByName(String userName) {
        return userDao.getUserById(findUserIdByName(userName));
    }

    public UserModel getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    public List<UserModel> getAllUsers() {
        return userDao.getAllUsers();
    }

    public int findUserIdByName(String userName) {
        return userDao.getUserIdByName(userName);
    }
}
