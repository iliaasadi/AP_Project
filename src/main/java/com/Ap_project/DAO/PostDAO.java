package com.Ap_project.DAO;

import com.Ap_project.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    private final Connection connection;

    public PostDAO() throws SQLException {
        this.connection = DataBase.getConnection();
    }

    public void creatPostTable() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS posts (postID VARCHAR(255) PRIMARY KEY ,userID VARCHAR (255) , message Varchar (500) , post_date DATE )");
        statement.executeUpdate();
    }

    public void savePost(Post post) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO posts (postID , userID , message , post_date) VALUES (? , ? , ? , ?)");
        statement.setString(1, post.getPostID());
        statement.setString(2, post.getUserId());
        statement.setString(3, post.getMessage());
        statement.setDate(4, post.getDate());


        statement.executeUpdate();
    }

    public void updatePost(Post post) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE posts SET  userID = ? , message = ? , post_date = ? WHERE postID = ?");
        statement.setString(1, post.getUserId());
        statement.setString(2, post.getMessage());
        statement.setDate(3, post.getDate());
        statement.setString(4, post.getUserId());


        statement.executeUpdate();
    }

    public void deletePost(String postID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM posts WHERE postID = ?");
        statement.setString(1, postID);
        statement.executeUpdate();
    }

    public void deleteAll() throws SQLException{
        PreparedStatement statement = connection.prepareStatement("DELETE FROM posts");
        statement.executeUpdate();
    }

    public Post getPost(String postID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM posts WHERE id = ?");
        statement.setString(1, postID);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            Post post = new Post();
            post.setPostID(resultSet.getString("postID"));
            post.setUserId(resultSet.getString("userID"));
            post.setMessage(resultSet.getString("message"));
            post.setDate(resultSet.getDate("post_date"));

            return post;
        }
        return null;
    }

    public List<Post> getAll() throws SQLException {
        ArrayList<Post> posts = new ArrayList<Post>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM posts");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Post post = new Post();
            post.setPostID(resultSet.getString("postID"));
            post.setUserId(resultSet.getString("userID"));
            post.setMessage(resultSet.getString("message"));
            post.setDate(resultSet.getDate("post_date"));
            posts.add(post);
        }
        return posts;
    }
}
