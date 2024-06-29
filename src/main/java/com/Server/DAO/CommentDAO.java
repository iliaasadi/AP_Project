package com.Server.DAO;

import com.Server.model.Comment;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private final Connection connection;

    public CommentDAO() throws SQLException {
        this.connection = DataBase.getConnection();
    }

    public void creatCommentTable() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS comments (commentID VARCHAR(255) PRIMARY KEY ,postID VARCHAR (255) ,userID VARCHAR (255), text Varchar (500) , comment_date DATE )");
        statement.executeUpdate();
    }

    public void saveComment(Comment comment) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO comments (commentID , postID , userID , text , comment_date) VALUES (? ,?, ? , ? , ?)");
        statement.setString(1 , comment.getCommentID());
        statement.setString(2, comment.getPostID());
        statement.setString(3, comment.getUserID());
        statement.setDate(4, comment.getDate());

        statement.executeUpdate();
    }

    public void updateComment(Comment comment) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE comments SET   text = ?  WHERE postID = ?");
        statement.setString(1, comment.getText());
        statement.setString(2, comment.getCommentID());


        statement.executeUpdate();
    }

    public void deleteComment(String commentID , String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM comments WHERE commentID = ?  AND user_id = ?");
        statement.setString(1, commentID);
        statement.setString(2, id);
        statement.executeUpdate();
    }

    public void deleteAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM comments");
        statement.executeUpdate();
    }

    public Comment getComment(String commentID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM posts WHERE commentID = ?");
        statement.setString(1, commentID);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Comment comment = new Comment();
            comment.setCommentID(resultSet.getString("commentID"));
            comment.setPostID(resultSet.getString("postID"));
            comment.setUserID(resultSet.getString("userID"));
            comment.setDate(resultSet.getDate("comment_date"));
            return comment;
        }
        return null;
    }

    public List<Comment> getAll() throws SQLException {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM comments");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Comment comment = new Comment();
            comment.setCommentID(resultSet.getString("commentID"));
            comment.setPostID(resultSet.getString("postID"));
            comment.setUserID(resultSet.getString("userID"));
            comment.setDate(resultSet.getDate("comment_date"));
            comments.add(comment);
        }
        return comments;
    }

    public ArrayList<Comment> getAllCommentsOfaPost (String post_id) throws SQLException, JsonProcessingException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM comments WHERE post_id = ?");

        statement.setString(1, post_id);

        ResultSet resultSet = statement.executeQuery();
        ArrayList<Comment> comments = new ArrayList<>();

        while (resultSet.next()) {
            Comment comment = new Comment(resultSet.getString("comment_id"), resultSet.getString("post_id"), resultSet.getString("user_id"), resultSet.getString("comment_text"));
            comments.add(comment);
        }

        return comments;
    }

    public boolean isCommentExist(String comment_id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM comments WHERE comment_id = ?");
        statement.setString(1, comment_id);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    public boolean isCommentExist(String comment_id, String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM comments WHERE comment_id = ? AND user_id = ?");
        statement.setString(1, comment_id);
        statement.setString(2, id);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }
}
