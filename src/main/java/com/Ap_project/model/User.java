package com.Ap_project.model;
import java.time.LocalDate;
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

    private LocalDate joinDate;

    public User(String email, String firstName, String lastName, String passWord, String additionalName, contactInfo contactInfo, JobPosition jobPosition, Education education) {
        this.email = email;
        this.firstName = firstName;
        this.LastName = lastName;
        this.passWord = passWord;
        this.additionalName = null;
        this.contactInfo = null;
        this.jobPosition = null;
        this.education = null;
        this.joinDate = LocalDate.now();
    }
    public User(){

    }

}
