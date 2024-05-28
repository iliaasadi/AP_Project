package com.Ap_project.model;

public class Follow {
    private String follower;
    private String followed;

    public Follow(String followerID, String followedID) {
        this.follower = followerID;
        this.followed = followedID;
    }

    public Follow(){
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowed() {
        return followed;
    }

    public void setFollowed(String followed) {
        this.followed = followed;
    }
}
