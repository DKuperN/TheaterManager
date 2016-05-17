package by.core.daos;

import by.core.models.UserModel;

import java.util.List;
import java.util.Set;

public interface UserDAO<U> {
    void saveNewUser(String userName, String userEmail);
    void deleteUser(int userId);
    UserModel getUserById(int id);
    int getUserIdByName(String userName);
    List<UserModel> getAllUsers();
    Set<Integer> getUsersTickets(int userID);
}
