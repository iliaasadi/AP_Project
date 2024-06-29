package com.Server.controller;

import com.Server.DAO.PostDAO;
import com.Server.model.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostController {
    private final PostDAO postDAO;

    public PostController() throws SQLException {
        this.postDAO = new PostDAO();
    }

    public void creatPost(String userId, String message, Date date) throws SQLException {
        Post post  = new Post(userId , message , date);
        postDAO.savePost(post);
    }

    public void updatePost(String userId, String postID, String message) throws SQLException {
        Post post = new Post(userId, postID, message);
        postDAO.updatePost(post);
    }

    public void deletePost(String postID) throws SQLException {
        postDAO.deletePost(postID);
    }

    public void deleteAll() throws SQLException {
        postDAO.deleteAll();
    }

    public String getPost(String postID) throws SQLException, JsonProcessingException {
        Post post = postDAO.getPost(postID);
        if(post == null) return null;
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(post);
    }

    public String getAll() throws SQLException, JsonProcessingException {
        List<Post> posts = postDAO.getAll();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(posts);
    }
    public void deleteAllPostsOfaUser(String id) throws SQLException {
        postDAO.deleteAllPostsOfaUser(id);
    }
    public String getAllPostsOfaUser(String id) throws SQLException, JsonProcessingException {
        ArrayList<Post> posts = postDAO.getAllPostsOfaUser(id);
        if (posts.isEmpty()) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(posts);
    }

}
