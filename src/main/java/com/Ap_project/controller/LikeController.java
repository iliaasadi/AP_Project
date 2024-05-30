package com.Ap_project.controller;


import com.Ap_project.DAO.LikeDAO;
import com.Ap_project.model.Like;

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
    public List<Like> getLikes(String Id) throws SQLException {
        List<Like> likes = likeDAO.getLikes(Id);
        return likes;
    }


    /**
     * json
     *
     * @param Id
     * @return
     * @throws SQLException
     */
    public List<Like> getLikers(String Id) throws SQLException {
        List<Like> likes = likeDAO.getLikers(Id);
        return likes;
    }


    /**
     * json
     *
     * @return
     * @throws SQLException
     */
    public List<Like> getAll() throws SQLException {
        List<Like> likes = likeDAO.getAll();
        return likes;
    }
}