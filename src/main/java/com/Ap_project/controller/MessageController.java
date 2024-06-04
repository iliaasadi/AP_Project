package com.Ap_project.controller;

import com.Ap_project.DAO.MessageDAO;
import com.Ap_project.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class MessageController {
    private final MessageDAO messageDAO;

    public MessageController() throws SQLException {
        messageDAO = new MessageDAO();
    }

    public void creatMessage(String id, String sender, String receiver, String text) throws SQLException {
        Message message = new Message(id, sender, receiver, text);
        messageDAO.saveMessage(message);
    }

    public String getMessages(String u1, String u2) throws SQLException, JsonProcessingException {
        ArrayList<Message> messages = messageDAO.getMessages(u1, u2);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(messages);
    }

    public void deleteMessage(String id) throws SQLException {
        messageDAO.deleteMessage(id);
    }

    public void deleteAll() throws SQLException {
        messageDAO.deleteAll();
    }

    public String getNotify(String receiver, int cnt) throws SQLException, JsonProcessingException {
        ArrayList<Message> messages = messageDAO.getNotify(receiver);
        cnt = Integer.min(cnt, messages.size());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(messages.subList(messages.size() - cnt, messages.size()));
    }
}
