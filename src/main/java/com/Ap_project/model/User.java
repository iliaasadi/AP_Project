package com.Ap_project.model;
import java.util.ArrayList;

public class User  {
    private String email;
    private String firstName;
    private String LastName;
    private String passWord;
    private String additionalName;
    private contactInfo contactInfo;
    private ArrayList<JobPosition> jobPosition;
    private ArrayList<Education> education;


    public User(String email, String firstName, String lastName, String passWord, String additionalName, contactInfo contactInfo, JobPosition jobPosition, Education education) {
        this.email = email;
        this.firstName = firstName;
        LastName = lastName;
        this.passWord = passWord;
        this.additionalName = null;
        this.contactInfo = null;
        this.jobPosition = null;
        this.education = null;
    }

    public contactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(contactInfo contactInfo) {
        this.contactInfo = contactInfo;
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

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", additionalName='" + additionalName + '\'' +
                '}';
    }
}
