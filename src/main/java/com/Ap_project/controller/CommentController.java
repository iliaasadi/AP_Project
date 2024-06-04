package com.Ap_project.controller;

import com.Ap_project.DAO.CommentDAO;
import com.Ap_project.model.Comment;
import com.Ap_project.model.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentController {
    private final CommentDAO commentDAO;

    public CommentController() throws SQLException {
        this.commentDAO = new CommentDAO();
    }

    public void creatComment(String commentID, String postID, String userID, String text, Date date) throws SQLException {
        Comment comment = new Comment(commentID , postID , userID , text , date);
        commentDAO.saveComment(comment);
    }

    public void updateComment(String commentID, String postID, String userID, String text, Date date) throws SQLException {
        Comment comment = new Comment(commentID, postID, userID, text, date);
        commentDAO.updateComment(comment);
    }

    public void deleteComment(String commentID) throws SQLException {
        commentDAO.deleteComment(commentID);
    }

    public void deleteAll() throws SQLException {
        commentDAO.deleteAll();
    }

    public String getComment(String commentID) throws SQLException, JsonProcessingException {
        Comment comment = commentDAO.getComment(commentID);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(comment);
    }

    public String getAll() throws JsonProcessingException, SQLException {
        List<Comment> comments = commentDAO.getAll();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(comments);
    }
}
