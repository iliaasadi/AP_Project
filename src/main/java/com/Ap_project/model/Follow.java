package com.Ap_project.model;

public class Follow {
    private String followerID;
    private String followedID;

    public Follow(String followerID, String followedID) {
        this.followerID = followerID;
        this.followedID = followedID;
    }

    public Follow() {
    }
}
