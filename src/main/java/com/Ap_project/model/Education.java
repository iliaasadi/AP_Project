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

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public Date getEducationStartDate() {
        return educationStartDate;
    }

    public void setEducationStartDate(Date educationStartDate) {
        this.educationStartDate = educationStartDate;
    }

    public Date getEducationFinishDate() {
        return educationFinishDate;
    }

    public void setEducationFinishDate(Date educationFinishDate) {
        this.educationFinishDate = educationFinishDate;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getEducationalActivitiesDescription() {
        return educationalActivitiesDescription;
    }

    public void setEducationalActivitiesDescription(String educationalActivitiesDescription) {
        this.educationalActivitiesDescription = educationalActivitiesDescription;
    }

    public String getEducationalDescription() {
        return educationalDescription;
    }

    public void setEducationalDescription(String educationalDescription) {
        this.educationalDescription = educationalDescription;
    }

    public ArrayList<String> getEducationalSkills() {
        return educationalSkills;
    }

    public void setEducationalSkills(ArrayList<String> educationalSkills) {
        this.educationalSkills = educationalSkills;
    }

    public boolean isChangeNotification() {
        return changeNotification;
    }

    public void setChangeNotification(boolean changeNotification) {
        this.changeNotification = changeNotification;
    }
}
