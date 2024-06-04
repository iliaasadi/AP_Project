package com.Server.controller;

import com.Server.DAO.HashTagDAO;
import com.Server.model.HashTag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.HashMap;

public class HashTagController {
    private final HashTagDAO hashTagDAO;

    public HashTagController() throws SQLException {
        hashTagDAO = new HashTagDAO();
    }

    public String getByHashTag(String tag) throws SQLException, JsonProcessingException {
        HashTag hashTag = new HashTag(tag);
        HashMap<Integer, String> result = hashTagDAO.getByHashTag(hashTag);
        if (result.isEmpty()) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(result);
    }
}
