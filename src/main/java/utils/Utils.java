package utils;

import models.AuditoriumModel;
import models.UserModel;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Utils {

    private DataSource dataSource;

    public Utils(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Properties prop = new Properties();
    private InputStream inputStream = getClass().getClassLoader().getResourceAsStream("app.properties");

    public String getPropertyByName(String propertyName) throws IOException {
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file 'app.properties' not found in the classpath");
        }
        inputStream.close();
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
                if(parameters.get(i) instanceof String){
                    ps.setString(i, String.valueOf(parameters.get(i)));
                } else if(parameters.get(i) instanceof Time){
                    ps.setTime(i, (Time) parameters.get(i));
                } else if(parameters.get(i) instanceof Date){
                    java.util.Date utilDate = (Date) parameters.get(i);
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    ps.setDate(i, sqlDate);
                } else if(parameters.get(i) instanceof Integer){
                    ps.setInt(i, (Integer) parameters.get(i));
                } else if(parameters.get(i) instanceof Double){
                    ps.setDouble(i, (Double) parameters.get(i));
                }

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

    public void executeQuery(String query, String parameter) throws SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, String.valueOf(parameter));
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

    public Date dateFormatter(String date) throws ParseException {
        DateFormat formatPattern = new SimpleDateFormat("yyyy-mm-dd");
        return (Date) formatPattern.parse(date);
    }
    public Time timeFormatter(String time) throws ParseException {
        DateFormat formatPattern = new SimpleDateFormat(time.contains(":") ? "HH:mm" : "HH.mm");
        return new Time(formatPattern.parse(time).getTime());
    }


    public Map<String, AuditoriumModel> loadAuditoriumsFromProperties() throws IOException {
        Properties properties = new Properties();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("auditoriums/auditoriumslist.properties");
        Map<String, AuditoriumModel> auditoriumModelList = new HashMap<String, models.AuditoriumModel>();

        Properties auditProperties = new Properties();
        AuditoriumModel auditoriumModel = null;

        if (stream != null) {
            properties.load(stream);
            for(Map.Entry<Object, Object> entry : properties.entrySet()){
                InputStream auditIstream = getClass().getClassLoader().getResourceAsStream("auditoriums/"+entry.getKey());
                auditProperties.load(auditIstream);
                auditoriumModel = new AuditoriumModel(
                        auditProperties.getProperty("name"),
                        Integer.parseInt(auditProperties.getProperty("capacity")),
                        parseVipSeatsToArr(auditProperties.getProperty("vipSeats"))
                );
                auditIstream.close();
                auditoriumModelList.put((entry.getKey().toString().split("\\-")[0]), auditoriumModel);
            }

        } else {
            throw new FileNotFoundException("property file 'app.properties' not found in the classpath");
        }

        stream.close();

        return auditoriumModelList;
    }

    private int[] parseVipSeatsToArr(String interval){
        String[] strArr = interval.split("\\-");
        int arrLeight = Integer.parseInt(strArr[1]) - Integer.parseInt(strArr[0])+1;
        int[] arr = new int[arrLeight];
        for (int i = 0; i < arrLeight; i++) {
            arr[i] = i + Integer.parseInt(strArr[0]);
        }
        return arr;
    }


    public Double getResultPrice(double basePriceForTicket, boolean placeVip, int eventRating) throws IOException {
        Double price = basePriceForTicket;
        String coefficientVip = getPropertyByName("coefficient.vip");
        if(placeVip) {
            price = basePriceForTicket * Double.parseDouble(coefficientVip);
        }
        switch (eventRating) {
            case 1:
                price = price * 1.2;
                break;
            case 2:
                price = price * 1.1;
                break;
            default:
                break;
        }

        return new BigDecimal(price).setScale(2, RoundingMode.UP).doubleValue();
    }
}
