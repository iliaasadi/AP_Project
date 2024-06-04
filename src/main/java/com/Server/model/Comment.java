package com.Server.model;

import java.sql.Date;

public class Comment {
    private String commentID;
    private String postID;
    private String userID;
    private String text;
    private Date date;

    public Comment(String commentID, String postID,String userID, String text, Date date) {
        this.commentID = commentID;
        this.postID = postID;
        this.userID = userID;
        this.text = text;
        this.date = date;
    }

    public Comment(){
    }

    public String getCommentID() {
        return commentID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
