package com.Server.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private final static String url = "jdbc:mysql://localhost:3306/linked-in";
    private final static String user = "root";
    private final static String password = "Il12345678";

    private static Connection connection;
    private DataBase(){
    }
     public static Connection getConnection() throws SQLException{
        if (connection == null) {
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } else return connection;
     }


 }
