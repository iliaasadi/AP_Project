package com.Server.DAO;

import com.Server.model.HashTag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class HashTagDAO {
    private final Connection connection;

    public HashTagDAO() throws SQLException {
        connection = DataBase.getConnection();
    }



    public HashMap<Integer, String> getByHashTag(HashTag hashTag) throws SQLException {
        HashMap<Integer, String> result = new HashMap<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM posts");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("post_id");
            String post_text = resultSet.getString("post_text");
            if (post_text.contains(hashTag.getTag())) {
                result.put(id, post_text);
            }
        }
        return result;
    }
}
