package daos.impl;

import daos.UserDAO;
import models.UserModel;
import utils.Utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UserDaoImpl implements UserDAO {

    private DataSource dataSource;
    private Utils utils;

    public UserDaoImpl(DataSource dataSource, Utils utils) {
        this.dataSource = dataSource;
        this.utils = utils;
    }

    public void saveNewUser(String userName, String userEmail) {
        final String SQL_SAVE_USER = "INSERT INTO USERS (USERNAME, USEREMAIL) VALUES (?, ?)";
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, userName);
        map.put(2, userEmail);
        utils.executeQuery(SQL_SAVE_USER, map);
    }

    public void deleteUser(int userID) {
        final String SQL_DELETE_NEW_USER = "DELETE FROM USERS WHERE USERID = ?";
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1, userID);
        utils.executeQuery(SQL_DELETE_NEW_USER, map);
    }

    public UserModel getUserById(int userId) {
        final String SQL_SEARCH_USER_BY_ID = "SELECT * FROM USERS WHERE USERID = ?";

//        UserModel userModel = new UserModel();
        HashMap<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("USERID", Integer.class);
        parameterMap.put("USERNAME", String.class);
        parameterMap.put("USEREMAIL", String.class);
//        return utils.getObjectFromQuery(SQL_SEARCH_USER_BY_ID, userId, parameterMap, ???);

        //TODO need refactoring - move this code to Utils.java
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
                System.out.println("Object with that parameters is not founded");
            }
        } catch (SQLException e) {
            System.out.println("FAIL: something wrong with connection or query");
            e.printStackTrace();
        } finally {
            utils.closeConnection(connection);
        }
        return userModel;
    }

    public int getUserIdByName(String userName) {
        String parameterName = userName.contains("@") ? "USEREMAIL" : "USERNAME";
        final String SQL_SEARCH_USER_BY_NAME = "SELECT * FROM USERS WHERE " + parameterName + " = ?";
        return utils.executeWithReturnInt(SQL_SEARCH_USER_BY_NAME, userName, "USERID");
    }

}
