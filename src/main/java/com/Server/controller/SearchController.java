package com.Server.controller;

import com.Server.DAO.SearchDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class SearchController {
    private SearchDAO searchDAO;

    public SearchController() throws SQLException {
        searchDAO = new SearchDAO();
    }

    public String searchUser(String search) throws SQLException, JsonProcessingException {
        ArrayList<String> idList = searchDAO.searchUser(search);
        if (idList.isEmpty()) {
            return null;
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(idList);
        }
    }
}
