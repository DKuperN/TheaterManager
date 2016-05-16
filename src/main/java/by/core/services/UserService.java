package by.core.services;

import by.core.models.UserModel;

import java.util.List;

/**
 * Created by Dzianis_Kupryianchyk on 02-May-16.
 */
public interface UserService {

    void registerUser(String userName, String email);

    void removeUser(String userName);

    UserModel getUserByName(String userName);
    UserModel getUserById(int userId);

    List<UserModel> getAllUsers();

}
