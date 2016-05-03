package daos.impl;

import daos.UserDAO;
import models.UserModel;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

//TODO need optimise queries

public class UserDaoImpl implements UserDAO<UserModel> {

    private DataSource dataSource;

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveNewUser(String userName, String userEmail) {
        final String SQL_SAVE_NEW_USER = "INSERT INTO USERS (USERNAME, USEREMAIL) VALUES (?, ?)";
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, userName);
        map.put(2, userEmail);
        executeQuery(SQL_SAVE_NEW_USER, map);
    }

    public void deleteUser(int userID) {
        final String SQL_SAVE_NEW_USER = "DELETE FROM USERS WHERE USERID = ?";
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1, userID);
        executeQuery(SQL_SAVE_NEW_USER, map);
    }

    public UserModel getUserById(int userId) {
        final String SQL_SEARCH_USER_BY_ID = "SELECT * FROM USERS WHERE USERID = ?";
        Connection connection = null;
        UserModel userModel = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SEARCH_USER_BY_ID);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                userModel = new UserModel(
                        rs.getInt("USERID"),
                        rs.getString("USERNAME"),
                        rs.getString("USEREMAIL")
                );
            }
            ps.close();
            if (userModel != null){
                return userModel;
            } else {
                System.out.println("User with that parameters is not founded");
            }
        } catch (SQLException e) {
            System.out.println("FAIL: something wrong with connection or query");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return userModel;
    }

    public int getUserIdByName(String userName) {
        String parameterName = userName.contains("@") ? "USEREMAIL" : "USERNAME";
        final String SQL_SEARCH_USER_BY_NAME = "SELECT * FROM USERS WHERE " + parameterName + " = ?";
        Connection connection = null;
        int id = 0;
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SEARCH_USER_BY_NAME);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt("USERID");
            }
            ps.close();
            return id;
        } catch (SQLException e) {
            System.out.println("FAIL: something wrong with connection or query");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return id;
    }

    private void executeQuery(String query, HashMap parameters){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            for (int i = 1; i < parameters.size()+1; i++) {
                ps.setString(i, String.valueOf(parameters.get(i)));
            }
            ps.executeUpdate();
            ps.close();
            System.out.println("Action OK!");
        } catch (SQLException e) {
            System.out.println("FAIL: something wrong with connection or query");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("FAIL: something wrong with closing connection");
            }
        }
    }

}
