package com.Ap_project.DAO;

import com.Ap_project.model.Comment;
import com.Ap_project.model.Post;

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

    public void updateComment(Comment comment) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE comments SET   text = ?  WHERE postID = ?");
        statement.setString(1, comment.getText());
        statement.setString(2, comment.getCommentID());


        statement.executeUpdate();
    }

    public void deleteComment(String commentID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM comments WHERE commentID = ?");
        statement.setString(1, commentID);
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
}
