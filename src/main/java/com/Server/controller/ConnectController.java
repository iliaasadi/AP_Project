package com.Server.controller;

import com.Server.DAO.ConnectDAO;
import com.Server.model.Connect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.List;

public class ConnectController {
    private final ConnectDAO connectDAO;

    public ConnectController() throws SQLException {
        this.connectDAO = new ConnectDAO();
    }

    public void creatConnect(String requestUserID, String acceptUserID/*, boolean accepted*/, String note) throws SQLException {
        Connect connect = new Connect(requestUserID , acceptUserID , note);
        connectDAO.saveConnect(connect);
    }

    public void deleteConnect(Connect connect) throws SQLException {
        connectDAO.deleteConnect(connect);
    }

    public void deleteAll() throws SQLException {
        connectDAO.deleteAll();
    }

    public String getConnecteds(String ID) throws SQLException, JsonProcessingException {
        List<Connect> connecteds = connectDAO.getConnecteds(ID);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(connecteds);
    }

    public String getAll() throws SQLException, JsonProcessingException{
        List<Connect> connections = connectDAO.getAll();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(connections);
    }

    public boolean isConnected(String first , String second) throws SQLException {
        return connectDAO.isConnected(first ,second);
    }
}
