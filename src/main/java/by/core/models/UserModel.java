package by.core.models;

/**
 * Created by Dzianis_Kupryianchyk on 02-May-16.
 */
public class UserModel {

    private String userName;

    private String userEmail;

    private int userId;

    public UserModel(int userId, String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
