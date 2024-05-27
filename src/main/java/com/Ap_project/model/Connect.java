package com.Ap_project.model;

public class Connect {
    private String requestUserID;
    private String acceptUserID;
    private boolean accepted;

    public Connect(String requestUserID, String acceptUserID, boolean accepted) {
        this.requestUserID = requestUserID;
        this.acceptUserID = acceptUserID;
        this.accepted = accepted;
    }

    public Connect() {
    }
}
