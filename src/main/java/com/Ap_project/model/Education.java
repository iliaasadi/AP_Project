package com.Ap_project.model;

import java.util.ArrayList;
import java.util.Date;

public class Education extends Date {
    private String instituteName;
    private String fieldOfStudy;
    private Date educationStartDate;
    private Date educationFinishDate;
    private float grade;
    private String educationalActivitiesDescription;
    private String educationalDescription;
    private ArrayList<String> educationalSkills;
    private boolean changeNotification;

    public Education(String instituteName, String fieldOfStudy, Date educationStartDate, Date educationFinishDate, float grade, String educationalActivitiesDescription, String educationalDescription, ArrayList<String> educationalSkills, boolean changeNotification) {
        this.instituteName = instituteName;
        this.fieldOfStudy = fieldOfStudy;
        this.educationStartDate = educationStartDate;
        this.educationFinishDate = educationFinishDate;
        this.grade = grade;
        this.educationalActivitiesDescription = educationalActivitiesDescription;
        this.educationalDescription = educationalDescription;
        this.educationalSkills = educationalSkills;
        this.changeNotification = changeNotification;
    }

    public Education(){}
}
