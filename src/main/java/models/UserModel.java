package models;

/**
 * Created by Dzianis_Kupryianchyk on 02-May-16.
 */
public class UserModel {

    //TODO maybe not needed
    public final static String TYPECODE = "User";

    //TODO maybe not needed
    public static final String ORDERS = "orders";

    private String userName;

    private String userEmail;

    private int userId;

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
