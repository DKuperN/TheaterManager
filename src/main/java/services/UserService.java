package services;

import models.UserModel;

/**
 * Created by Dzianis_Kupryianchyk on 02-May-16.
 */
public interface UserService {

    void registerUser(String userName, String email);

    void removeUser(String userName);

    UserModel getUserByName(String userName);
    UserModel getUserById(int userId);

}
