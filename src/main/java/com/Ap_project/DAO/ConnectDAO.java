package com.Ap_project.DAO;

import com.Ap_project.model.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectDAO {
    private final Connection connection;
    public ConnectDAO() throws SQLException {
        connection = DataBase.getConnection();
        creatConnectTable();
    }

    public void creatConnectTable() throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS connects (first_user VARCHAR(255), second_user VARCHAR(255),connect_note VARCHAR (500), PRIMARY KEY (first_user, second_user))");
        preparedStatement.executeUpdate();
    }

    public void saveConnect(Connect connect) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO connects (first_user, second_user, connect_note) VALUES (?, ?, ?)");
        preparedStatement.setString(1, connect.getFirstUser());
        preparedStatement.setString(2, connect.getSecondUser());
        preparedStatement.setString(3,connect.getConnectNote());
        preparedStatement.executeUpdate();
    }

    public void deleteConnect(Connect connect) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM connects WHERE first_user = ? AND second_user = ?");
        preparedStatement.setString(1, connect.getFirstUser());
        preparedStatement.setString(2, connect.getConnectNote());
        preparedStatement.executeUpdate();
    }

    public void deleteAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM connects");
        preparedStatement.executeUpdate();
    }

    public List<Connect> getConnecteds(String userId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM connects WHERE first_user = ?");
        preparedStatement.setString(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Connect> connects = new ArrayList<>();
        while (resultSet.next()) {
            Connect connect = new Connect();
            connect.setFirstUser(resultSet.getString("first_user"));
            connect.setFirstUser(resultSet.getString("second_user"));
            connect.setConnectNote(resultSet.getString("connect_note"));
            connects.add(connect);
        }
        return connects;
    }

    public List<Connect> getAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM connects");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Connect> connects = new ArrayList<>();
        while (resultSet.next()) {
            Connect connect = new Connect();
            connect.setFirstUser(resultSet.getString("first_user"));
            connect.setSecondUser(resultSet.getString("second_user"));
            connect.setConnectNote(resultSet.getString("connect_note"));
            connects.add(connect);
        }
        return connects;
    }

    public boolean isConnected(String firstUser, String secondUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM connects WHERE first_user = ? AND second_user = ?");
        preparedStatement.setString(1, firstUser);
        preparedStatement.setString(2, secondUser);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean isConnected = resultSet.next();
        return isConnected;
    }

}
