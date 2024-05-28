package com.Ap_project.model;

public class Connect {
    private String firstUser;
    private String secondUser;
    //private boolean accepted;

    private String connectNote;

    public Connect(String requestUserID, String acceptUserID/*, boolean accepted*/ ,String note) {
        this.firstUser = requestUserID;
        this.secondUser = acceptUserID;
        //this.accepted = accepted;
        this.connectNote = note;
    }

    public Connect() {
    }

    public String getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }

    public String getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(String secondUser) {
        this.secondUser = secondUser;
    }

 //   public boolean isAccepted() {
 //       return accepted;
 //   }
//
 //   public void setAccepted(boolean accepted) {
 //       this.accepted = accepted;
 //   }

    public String getConnectNote() {
        return connectNote;
    }

    public void setConnectNote(String connectNote) {
        this.connectNote = connectNote;
    }
}
