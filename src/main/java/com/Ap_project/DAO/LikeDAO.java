package com.Ap_project.DAO;

import com.Ap_project.model.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeDAO {
    private final Connection connection;

    public LikeDAO() throws SQLException {
        connection = DataBase.getConnection();
        createLikeTable();
    }

    private void createLikeTable() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS likes (liker VARCHAR(255), liked VARCHAR(255), PRIMARY KEY (liker, liked))");
        statement.executeUpdate();

    }

    public void saveLike(Like like) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO likes (liker, liked) VALUES (?, ?)");
        statement.setString(1, like.getLiker());
        statement.setString(2, like.getLiked());
        statement.executeUpdate();
    }

    public void deleteLike(Like like) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM likes WHERE liker = ? AND liked = ?");
        statement.setString(1, like.getLiker());
        statement.setString(2, like.getLiked());
        statement.executeUpdate();
    }

    public void deleteAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM likes");
        statement.executeUpdate();
    }

    /**
     * @param Id
     * @return id has liked what
     * @throws SQLException
     */
    public List<Like> getLikes(String Id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM likes WHERE liker = ?");
        statement.setString(1, Id);
        ResultSet resultSet = statement.executeQuery();
        List<Like> likes = new ArrayList<>();
        while (resultSet.next()) {
            Like like = new Like();
            like.setLiker(resultSet.getString("liker"));
            like.setLiked(resultSet.getString("liked"));
            likes.add(like);
        }
        return likes;
    }

    /**
     * @param Id
     * @return what has liked id
     * @throws SQLException
     */
    public List<Like> getLikers(String Id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM likes WHERE liked = ?");
        statement.setString(1, Id);
        ResultSet resultSet = statement.executeQuery();
        List<Like> likes = new ArrayList<>();
        while (resultSet.next()) {
            Like like = new Like();
            like.setLiker(resultSet.getString("liker"));
            like.setLiked(resultSet.getString("liked"));
            likes.add(like);
        }
        return likes;
    }

    public List<Like> getAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM likes");
        ResultSet resultSet = statement.executeQuery();
        List<Like> likes = new ArrayList<>();
        while (resultSet.next()) {
            Like like = new Like();
            like.setLiker(resultSet.getString("liker"));
            like.setLiked(resultSet.getString("liked"));
            likes.add(like);
        }
        return likes;
    }

    public boolean isLiking(String likerId, String likedId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM likes WHERE liker = ? AND liked = ?");
        statement.setString(1, likerId);
        statement.setString(2, likedId);
        ResultSet resultSet = statement.executeQuery();
        boolean isLiking = resultSet.next();
        return isLiking;
    }
}
