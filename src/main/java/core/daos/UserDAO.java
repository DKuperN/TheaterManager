package core.daos;

import core.models.UserModel;

import java.util.List;

public interface UserDAO<U> {
    void saveNewUser(String userName, String userEmail);
    void deleteUser(int userId);
    UserModel getUserById(int id);
    int getUserIdByName(String userName);
    List<UserModel> getAllUsers();
}
