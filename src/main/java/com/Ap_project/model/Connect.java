package com.Ap_project.model;

public class Connect {
    private String requestUserID;
    private String acceptUserID;
    private boolean accepted;

    private String connectNote;

    public Connect(String requestUserID, String acceptUserID, boolean accepted,String note) {
        this.requestUserID = requestUserID;
        this.acceptUserID = acceptUserID;
        this.accepted = accepted;
        this.connectNote = note;
    }

    public Connect() {
    }
}
