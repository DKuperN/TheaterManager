package utils;

import models.UserModel;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

public class Utils {

    private DataSource dataSource;

    public Utils(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Properties prop = new Properties();
    private InputStream inputStream = getClass().getClassLoader().getResourceAsStream("menu.properties");

    public String getPropertyByName(String propertyName) throws IOException {
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file 'menu.properties' not found in the classpath");
        }

        return prop.getProperty(propertyName);
    }

    public int executeWithReturnInt(String query, String qryParameter, String IDFieldNameInDB){
        Connection connection = null;
        int id = 0;
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, qryParameter);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt(IDFieldNameInDB);
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

    public void executeQuery(String query, HashMap parameters){
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

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("FAIL: something wrong with closing connection");
            }
        }
    }

    //TODO need refactoring
    public UserModel getObjectFromQuery(String sql, int rowId, HashMap<String, Object> parameterMap, UserModel dataModel) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, rowId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){

                dataModel = new UserModel(
                        rs.getInt("USERID"),
                        rs.getString("USERNAME"),
                        rs.getString("USEREMAIL")
                );
            }
            ps.close();
            return dataModel;
        } catch (SQLException e) {
            System.out.println("FAIL: something wrong with connection or query");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return dataModel;

    }
}
