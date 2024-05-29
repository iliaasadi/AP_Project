package com.Ap_project.model;

public class Like {
    private String likerId ;
    private String likedPostId ;

    public Like(String likerId, String likedPostId) {
        this.likerId = likerId;
        this.likedPostId = likedPostId;
    }

    public Like() {
    }

    public String getLikerId() {
        return likerId;
    }

    public void setLikerId(String likerId) {
        this.likerId = likerId;
    }

    public String getLikedPostId() {
        return likedPostId;
    }

    public void setLikedPostId(String likedPostId) {
        this.likedPostId = likedPostId;
    }
}
