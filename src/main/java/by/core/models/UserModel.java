package by.core.models;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Dzianis_Kupryianchyk on 02-May-16.
 */
public class UserModel {

    private String userName;
    private String userEmail;
    private int userId;
    private Set<Integer> bookedTickets = new TreeSet<>();

    public UserModel(int userId, String userEmail, String userName, Set<Integer> bookedTickets) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userId = userId;
        this.bookedTickets = bookedTickets;
    }

    public UserModel(int userId, String userEmail, String userName) {
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

    public Set<Integer> getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(Set<Integer> bookedTickets) {
        this.bookedTickets = bookedTickets;
    }
}
