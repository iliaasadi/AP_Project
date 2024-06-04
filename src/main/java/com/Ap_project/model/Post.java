package com.Ap_project.model;

public class Post {
    private String userId;
    private String postID;
    private String message;

    public Post(String userId, String postID, String message) {
        this.userId = userId;
        this.postID = postID;
        this.message = message;
    }

    public Post(){
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
