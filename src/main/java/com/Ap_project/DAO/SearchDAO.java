package com.Ap_project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchDAO {
    private Connection connection;

    public SearchDAO() throws SQLException {
        connection = DataBase.getConnection();
    }

    public ArrayList<String> searchUser(String search) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM users WHERE email LIKE ? OR firstname LIKE ? OR lastname LIKE ? OR country LIKE ? OR city LIKE ? OR additionalname LIKE ?");

        statement.setString(1, "%" + search + "%");
        statement.setString(2, "%" + search + "%");
        statement.setString(3, "%" + search + "%");
        statement.setString(4, "%" + search + "%");
        statement.setString(5, "%" + search + "%");
        statement.setString(6, "%" + search + "%");

        ResultSet resultSet = statement.executeQuery();

        ArrayList<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString("email"));
        }

        return idList;
    }
}
