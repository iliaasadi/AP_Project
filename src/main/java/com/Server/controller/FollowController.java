package com.Server.controller;

import com.Server.DAO.FollowDAO;
import com.Server.model.Follow;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.List;

public class FollowController {
    private final FollowDAO followDAO;

    public FollowController() throws SQLException {
        followDAO = new FollowDAO();
    }

    public void createFollowTable() throws SQLException {
        followDAO.createFollowTable();
    }

    public void saveFollow(String follower, String followed) throws SQLException {
        Follow follow = new Follow(follower, followed);
        followDAO.saveFollow(follow);
    }

    public void deleteFollow(String follower, String followed) throws SQLException {
        Follow follow = new Follow(follower, followed);
        followDAO.deleteFollow(follow);
    }

    public void deleteAll() throws SQLException {
        followDAO.deleteAll();
    }


    /**
     * json
     * @param userId
     * @return
     * @throws SQLException
     */
    public String getFollows(String userId) throws SQLException, JsonProcessingException {
        List<Follow> follows = followDAO.getFollows(userId);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(follows);
    }


    /**
     * json
     * @param userId
     * @return
     * @throws SQLException
     */
    public String getFollowers(String userId) throws SQLException, JsonProcessingException {
        List<Follow> follows = followDAO.getFollowers(userId);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(follows);
    }


    /**
     * json
     * @return
     * @throws SQLException
     */
    public String getAll() throws SQLException, JsonProcessingException {
        List<Follow> follows = followDAO.getAll();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(follows);
    }
}