package com.Server.controller;

import com.Server.DAO.CommentDAO;
import com.Server.model.Comment;
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

    public void creatComment( String postID, String userID, String text, Date date) throws SQLException {
        Comment comment = new Comment( postID , userID , text , date);
        commentDAO.saveComment(comment);
    }

    public void updateComment(String commentID,  String userID, String text) throws SQLException {
        Comment comment = new Comment(commentID, userID, text);
        commentDAO.updateComment(comment);
    }

    public void deleteComment(String commentID,String id) throws SQLException {
        commentDAO.deleteComment(commentID , id);
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

    public String getAllCommentsOfaPost(String post_id) throws SQLException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Comment> comments = commentDAO.getAllCommentsOfaPost(post_id);
        if (comments.isEmpty()) {
            return null;
        } else {
            return objectMapper.writeValueAsString(comments);
        }
    }

    public boolean isCommentExist(String comment_id) throws SQLException {
        return commentDAO.isCommentExist(comment_id);
    }

    public boolean isCommentExist(String comment_id, String id) throws SQLException {
        return commentDAO.isCommentExist(comment_id, id);
    }
}
