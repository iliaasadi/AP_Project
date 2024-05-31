package com.Ap_project.controller;

import com.Ap_project.DAO.FollowDAO;
import com.Ap_project.model.Follow;

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
    public List<Follow> getFollows(String userId) throws SQLException {
        List<Follow> follows = followDAO.getFollows(userId);
        return follows;
    }


    /**
     * json
     * @param userId
     * @return
     * @throws SQLException
     */
    public List<Follow> getFollowers(String userId) throws SQLException {
        List<Follow> follows = followDAO.getFollowers(userId);
        return follows;
    }


    /**
     * json
     * @return
     * @throws SQLException
     */
    public List<Follow> getAll() throws SQLException {
        List<Follow> follows = followDAO.getAll();
        return follows;
    }
}