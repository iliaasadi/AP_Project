package com.Ap_project.DAO;

import com.Ap_project.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private final Connection connection;


    public UserDAO(Connection connection) throws SQLException {
        this.connection = connection;
        createUserTable();
    }

    public void createUserTable() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (id VARCHAR(36) PRIMARY KEY ,first_name VARCHAR(20) , last_name VARCHAR(40) , email VARCHAR(255) UNIQUE , password VARCHAR(255) , additional_name VARCHAR(80) , join_date DATE )");
        statement.executeUpdate();
    }

    public void saveUSer(User user)throws SQLException{
        PreparedStatement statement =connection.prepareStatement("INSERT INTO users (id, first_name , last_name , email , password , additional_name , join_date) VALUES (?,?,?,?,?,?,?) ");
        statement.setString(1,user.getID());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getLastName());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getPassWord());
        statement.setString(6, user.getAdditionalName());
        statement.setDate(7, user.getJoinDate());

        statement.executeUpdate();
    }

    public void deleteUser(User user) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
        statement.setString(1, user.getID());
        statement.executeUpdate();
    }

    public void deleteUsers() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM users");
        statement.executeUpdate();
    }

    public void updateUser(User user)throws SQLException{
        PreparedStatement statement = connection.prepareStatement("UPDATE users SET first_name = ? , last_name = ?, email = ?, password = ?,additional_name = ? WHERE id = ?");
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());
        statement.setString(5, user.getAdditionalName());
        statement.setString(6, user.getID());

        statement.executeUpdate();
    }

    /**
     * getting user only using user ID
     * @param userID
     * @return
     * @throws SQLException
     */
    public User getUser(String userID)throws SQLException{
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        statement.setString(1 , userID);
        ResultSet resultSet =statement.executeQuery();
        if(resultSet.next()){
            User user = new User();
            user.setID(resultSet.getString("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setAdditionalName(resultSet.getString("additional_name"));
            user.setPassWord(resultSet.getString("password"));
            user.setJoinDate(resultSet.getDate("join_date"));
            return user;
        }
        return null;
    }


    /**
     * getting user  using pass and id (probably to get our own profile)
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
            user.setAdditionalName(resultSet.getString("additional_name"));
            user.setPassWord(resultSet.getString("password"));
            user.setJoinDate(resultSet.getDate("join_date"));
            return user;
        }
        return null;
    }

    public ArrayList<User> getUsers()throws SQLException{
        ArrayList<User> users = new ArrayList<User>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            User user = new User();
            user.setID(resultSet.getString("id"));
            user.setID(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassWord(resultSet.getString("password"));
            user.setAdditionalName(resultSet.getString("additional_name"));
            user.setJoinDate(resultSet.getDate("join_date"));
            users.add(user);
        }
        return users;
    }

}