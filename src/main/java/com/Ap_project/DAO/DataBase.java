package com.Ap_project.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private final static String url = "jdbc:mysql://170.0.0.1:3386/MyLinkedIn";
    private final static String user = "root";
    private final static String password = "Emami2005";

    private DataBase(){
    }
     public static Connection getConnection() throws SQLException{
        Connection connection = null;
        connection = DriverManager.getConnection(url , user , password);
        return connection;
     }


 }
