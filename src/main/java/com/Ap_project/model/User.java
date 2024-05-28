package com.Ap_project.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class User {
    private String ID;
    private String email;
    private String firstName;
    private String LastName;
    private String passWord;
    private String additionalName;
    private contactInfo contactInfo;
    private ArrayList<JobPosition> jobPosition;
    private ArrayList<Education> education;
    private Date joinDate;

    /**
     * private Date lastLogIn;
     * and
     * private String status;   //want to be hired , looking for a job , want to provide service.
     */


    public User(String ID, String email, String firstName, String lastName, String passWord, Date joinDate) {
        this.ID = ID;
        this.email = email;
        this.firstName = firstName;
        this.LastName = lastName;
        this.passWord = passWord;
        this.additionalName = null;
        this.joinDate = joinDate;
        jobPosition = new ArrayList<>();
        education = new ArrayList<>();
    }

    public User() {

    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAdditionalName() {
        return additionalName;
    }

    public void setAdditionalName(String additionalName) {
        this.additionalName = additionalName;
    }

    public com.Ap_project.model.contactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(com.Ap_project.model.contactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public ArrayList<JobPosition> getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(ArrayList<JobPosition> jobPosition) {
        this.jobPosition = jobPosition;
    }

    public ArrayList<Education> getEducation() {
        return education;
    }

    public void setEducation(ArrayList<Education> education) {
        this.education = education;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}
