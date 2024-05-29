package com.Ap_project.model;

public class Like {

    private String liker;
    private String liked;

    public Like(String liker, String liked) {
        this.liker = liker;
        this.liked = liked;
    }

    public Like() {
    }

    public String getLiker() {
        return liker;
    }

    public void setLiker(String liker) {
        this.liker = liker;
    }

    public String getLiked() {
        return liked;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }
}
