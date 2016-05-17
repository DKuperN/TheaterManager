package by.core.services.impl;

import by.core.daos.impl.UserDaoImpl;
import by.core.models.UserModel;
import by.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDaoImpl userDao;

    public UserServiceImpl() {}

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
        UserModel userModel = userDao.getUserById(userId);
        populateUserModel(userModel, userId);
        return userModel;
    }

    private void populateUserModel(UserModel userModel, int userId) {
        userModel.setBookedTickets(userDao.getUsersTickets(userId));
    }

    public List<UserModel> getAllUsers() {
        return userDao.getAllUsers();
    }

    public int findUserIdByName(String userName) {
        return userDao.getUserIdByName(userName);
    }
}
