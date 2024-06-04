package com.Ap_project.model;

import java.sql.Date;

import java.util.ArrayList;


public class Education {
    private String Id;
    private String instituteName;
    private String fieldOfStudy;
    private Date educationStartDate;
    private Date educationFinishDate;
    private float grade;

    // ""shrhe faaliat"" va ""anjoman""  fargh darad ba ""tozihat"" 2ta field jodan
    private String educationalActivitiesDescription;
    private String educationalDescription;
//    private Skill educationalSkills;
    enum notification {
        ME,
        CONTACTS,
        EVERYONE
    }
    private notification eduNotification;
    public Education(String Id, String instituteName, String fieldOfStudy, Date educationStartDate, Date educationFinishDate, float grade, String educationalActivitiesDescription, String educationalDescription, Skill educationalSkills, String notificationEduStr) {
        this.Id = Id;
        this.instituteName = instituteName;
        this.fieldOfStudy = fieldOfStudy;
        this.educationStartDate = educationStartDate;
        this.educationFinishDate = educationFinishDate;
        this.grade = grade;
        this.educationalActivitiesDescription = educationalActivitiesDescription;
        this.educationalDescription = educationalDescription;
        //this.educationalSkills = educationalSkills;
        switch (notificationEduStr) {
            case "me" -> this.eduNotification = notification.ME;
            case "contacts" -> this.eduNotification = notification.CONTACTS;
            case "everyone" -> this.eduNotification = notification.EVERYONE;
        }
    }

    public Education() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
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
//
//    public Skill getEducationalSkills() {
//        return educationalSkills;
//    }
//
//    public void setEducationalSkills(Skill educationalSkills) {
//        this.educationalSkills = educationalSkills;
//    }


    public String getEduNotification() {
        return eduNotification.name().toLowerCase();
    }

    public void setEduNotification(String notificationEduStr){
        switch (notificationEduStr) {
            case "me" -> this.eduNotification = notification.ME;
            case "contacts" -> this.eduNotification = notification.CONTACTS;
            case "everyone" -> this.eduNotification = notification.EVERYONE;
        }
    }
}
