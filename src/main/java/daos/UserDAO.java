package daos;

import models.UserModel;

public interface UserDAO<U> {
    void saveNewUser(String userName, String userEmail);
    void deleteUser(int userId);
    UserModel getUserById(int id);
    UserModel getUserByName(String userName);
    UserModel getUserByEmail(String userEmail);
    int getUserIdByName(String userName);
}
