package com.Ap_project.DAO;

import com.Ap_project.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private final Connection connection;


    public UserDAO() throws SQLException {
        this.connection = DataBase.getConnection();
        createUserTable();
    }

    public void createUserTable() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (id VARCHAR(255) PRIMARY KEY ,first_name VARCHAR(255) , last_name VARCHAR(255) , email VARCHAR(255) UNIQUE , password VARCHAR(255) , additional_name VARCHAR(255),city VARCHAR (255),country VARCHAR (255), join_date DATE,work_type VARCHAR (255) )");
        statement.executeUpdate();
    }

    public void saveUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO users (id, first_name , last_name , email , password , additional_name,city , country , join_date, work_type) VALUES (?,?,?,?,?,?,?,?,?,?) ");
        statement.setString(1, user.getID());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getLastName());
        statement.setString(4, user.getEmail().toLowerCase());
        statement.setString(5, user.getPassWord());
        statement.setString(6, user.getAdditionalName());
        statement.setString(7, user.getCity());
        statement.setString(8, user.getCountry());
        statement.setDate(9, user.getJoinDate());
        statement.setString(10, user.getWorkType());

        statement.executeUpdate();
    }

    public void deleteUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
        statement.setString(1, user.getID());
        statement.executeUpdate();
    }

    public void deleteUsers() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM users");
        statement.executeUpdate();
    }


    public void updateUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE users SET first_name = ? , last_name = ?, email = ?, password = ?,additional_name = ?,city = ? , country = ? , work_type =? WHERE id = ?");

        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail().toLowerCase());
        statement.setString(4, user.getPassWord());
        statement.setString(5, user.getAdditionalName());
        statement.setString(6, user.getCity());
        statement.setString(7, user.getCountry());
        statement.setString(8, user.getWorkType());
        statement.setString(9, user.getID());

        statement.executeUpdate();
    }

    /**
     * getting user only using user ID
     *
     * @param userID
     * @return
     * @throws SQLException
     */
    public User getUser(String userID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        statement.setString(1, userID);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            User user = new User();
            user.setID(resultSet.getString("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassWord(resultSet.getString("password"));
            user.setAdditionalName(resultSet.getString("additional_name"));
            user.setCity(resultSet.getString("city"));
            user.setCountry(resultSet.getString("country"));
            user.setJoinDate(resultSet.getDate("join_date"));
            user.setWorkType(resultSet.getString("work_type"));
            return user;
        }
        return null;
    }

    public boolean isUserExist(String email) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT email FROM users WHERE email = ?");
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }


    /**
     * getting user  using pass and id (probably to get our own profile)
     *
     * @param userID
     * @param userPass
     * @return
     * @throws SQLException
     */
    public User getUser(String userID, String userPass) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ? AND password = ?");
        statement.setString(1, userID);
        statement.setString(2, userPass);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            User user = new User();
            user.setID(resultSet.getString("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassWord(resultSet.getString("password"));
            user.setAdditionalName(resultSet.getString("additional_name"));
            user.setCity(resultSet.getString("city"));
            user.setCountry(resultSet.getString("country"));
            user.setJoinDate(resultSet.getDate("join_date"));
            user.setWorkType(resultSet.getString("work_type"));
            return user;
        }
        return null;
    }

    public ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            User user = new User();
            user.setID(resultSet.getString("id"));
            user.setID(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassWord(resultSet.getString("password"));
            user.setAdditionalName(resultSet.getString("additional_name"));
            user.setCity(resultSet.getString("city"));
            user.setCountry(resultSet.getString("country"));
            user.setJoinDate(resultSet.getDate("join_date"));
            user.setWorkType(resultSet.getString("work_type"));
            users.add(user);
        }

        return users;
    }

}