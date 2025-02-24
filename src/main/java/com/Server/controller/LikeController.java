package com.Server.controller;


import com.Server.DAO.LikeDAO;
import com.Server.model.Like;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.List;

public class LikeController {
    private final LikeDAO likeDAO;

    public LikeController() throws SQLException {
        likeDAO = new LikeDAO();
    }

    public void createLikeTable() throws SQLException {
        likeDAO.createLikeTable();
    }

    public void saveLike(String liker, String liked) throws SQLException {
        Like like = new Like(liker, liked);
        likeDAO.saveLike(like);
    }

    public void deleteLike(String liker, String liked) throws SQLException {
        Like like = new Like(liker, liked);
        likeDAO.deleteLike(like);
    }

    public void deleteAll() throws SQLException {
        likeDAO.deleteAll();
    }

    /**
     * json
     * @param Id
     * @return
     * @throws SQLException
     */
    public String getLikes(String Id) throws SQLException, JsonProcessingException { // of a person
        List<Like> likes = likeDAO.getLikes(Id);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(likes);
    }


    /**
     * json
     *
     * @param Id
     * @return
     * @throws SQLException
     */
    public String getLikers(String Id) throws SQLException, JsonProcessingException { //of a post
        List<Like> likes = likeDAO.getLikers(Id);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(likes);
    }


    /**
     * json
     *
     * @return
     * @throws SQLException
     */
    public String getAll() throws SQLException, JsonProcessingException {
        List<Like> likes = likeDAO.getAll();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(likes);
    }
    public boolean likeExists(String email, String postId) throws SQLException {
        return likeDAO.isLiking(email, postId);
    }
}