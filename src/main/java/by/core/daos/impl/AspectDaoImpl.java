package by.core.daos.impl;

import by.core.daos.AspectDAO;
import by.core.models.AspectsModel;
import by.utils.Utils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AspectDaoImpl implements AspectDAO {

    private DataSource dataSource;
    private Utils utils;

    public AspectDaoImpl(DataSource dataSource, Utils utils) {
        this.dataSource = dataSource;
        this.utils = utils;
    }

    @Override
    public AspectsModel getAspectByName(String counterName, Time aspectTime) {
        Connection connection = null;
        AspectsModel aspectsModel = null;
        int aspectId = getAspectId(counterName, aspectTime);
        final String SQL_SEARCH_ASPECT_BY_NAME = "SELECT * FROM ASPECTS_COUNTER WHERE ASPECTID = " + aspectId + "";

        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SEARCH_ASPECT_BY_NAME);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                aspectsModel = new AspectsModel(
                        rs.getInt("aspectId"),
                        rs.getString("counterName"),
                        rs.getInt("counterQuantity"),
                        rs.getDate("date"),
                        rs.getTime("finalCountTime")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aspectsModel;
    }

    private int getAspectId(String counterName, Time aspectTime) {
        Connection connection = null;
        int id = 0;
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ASPECTS_COUNTER AC WHERE AC.counterName = ? AND AC.FINALCOUNTTIME = ?");
            ps.setString(1, counterName);
            ps.setTime(2, aspectTime);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt("ASPECTID");
            }
            ps.close();
            return id;
        } catch (SQLException e) {
            System.out.println("FAIL: something wrong with connection or query");
            e.printStackTrace();
        } finally {
            utils.closeConnection(connection);
        }
        return id;
    }

    @Override
    public List<AspectsModel> getAllAspectCounters() {
        Connection connection = null;
        AspectsModel aspectsModel = null;
        List<AspectsModel> aspectsModelList = new ArrayList<>();
        final String SELECT_ALL_ASPECTS = "SELECT * FROM ASPECTS_COUNTER";
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_ASPECTS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                aspectsModel = new AspectsModel(
                        rs.getInt("aspectId"),
                        rs.getString("counterName"),
                        rs.getInt("counterQuantity"),
                        rs.getDate("date"),
                        rs.getTime("finalCountTime")
                );
                aspectsModelList.add(aspectsModel);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aspectsModelList;
    }

    @Override
    public void storeAspect(AspectsModel aspectsModel) {
        final String SQL_SAVE_ASPECT_COUNTER = "INSERT INTO ASPECTS_COUNTER (counterName, counterQuantity, date, finalCountTime) VALUES (?, ?, ?, ?)" +
                "ON DUPLICATE KEY UPDATE counterQuantity = ?, date = ?, finalCountTime = ?";
        HashMap<Integer, Object> map = new HashMap<>();
        map.put(1, aspectsModel.getCounterName());
        map.put(2, aspectsModel.getCounterQuantity());
        map.put(3, new java.sql.Date((aspectsModel.getCounterDateTime()).getTime()));
        map.put(4, aspectsModel.getFinalCounterTime());
        map.put(5, aspectsModel.getCounterQuantity());
        map.put(6, new java.sql.Date((aspectsModel.getCounterDateTime()).getTime()));
        map.put(7, aspectsModel.getFinalCounterTime());
        utils.executeQuery(SQL_SAVE_ASPECT_COUNTER, map);
    }
}
