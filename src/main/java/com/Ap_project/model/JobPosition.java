package com.Ap_project.model;

import java.sql.Date;
import java.util.ArrayList;

public class JobPosition {
    private String Id;
    private String title;

    /**
     * job type ---> has multi option:
     * part time , full time , self-employment , freelance , contractual , intern , paid intern , seasonal
     */
    private String jobType;
    private String companyName;
    private String companyAddress;
    /**
     * companyType ---> has multi option:
     * in Workplace or hybrid or telecommuting
     */
    private String companyType;
    /**
     * check box for occupationStatus
     */
    private String occupationStatus;
    private Date jobStartDate;
    private Date jobFinishDate;
    private String description;
    private ArrayList<String> skills = new ArrayList<>();
    private boolean activation;

    enum notification {
        ME,
        CONTACTS,
        EVERYONE
    }
    private notification jobNotification;


    public JobPosition(String Id, String title, String jobType, String companyName, String companyAddress, String companyType, String occupationStatus, Date jobStartDate, String description, String skill ,String notificationJobStr) {
        this.Id = Id;
        this.title = title;
        this.jobType = jobType;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyType = companyType;
        this.occupationStatus = occupationStatus;
        this.jobStartDate = jobStartDate;
        this.jobFinishDate = null;
        this.description = description;
        this.skills.add(skill);
        this.activation = true;
        //active
        switch (notificationJobStr) {
            case "me" -> this.jobNotification = notification.ME;
            case "contacts" -> this.jobNotification = notification.CONTACTS;
            case "everyone" -> this.jobNotification = notification.EVERYONE;
        }
    }

    public JobPosition(String Id, String title, String jobType, String companyName, String companyAddress, String companyType, String occupationStatus, Date jobStartDate, Date jobFinishDate, String description, String skill , String notificationJob) {
        this.Id = Id;
        this.title = title;
        this.jobType = jobType;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyType = companyType;
        this.occupationStatus = occupationStatus;
        this.jobStartDate = jobStartDate;
        this.jobFinishDate = jobFinishDate;
        this.description = description;
        this.skills.add(skill);
        this.activation = false;
        //not active
        switch (notificationJob) {
            case "me" -> this.jobNotification = notification.ME;
            case "contacts" -> this.jobNotification = notification.CONTACTS;
            case "everyone" -> this.jobNotification = notification.EVERYONE;
        }

    }

    public JobPosition() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getOccupationStatus() {
        return occupationStatus;
    }

    public void setOccupationStatus(String occupationStatus) {
        this.occupationStatus = occupationStatus;
    }

    public Date getJobStartDate() {
        return jobStartDate;
    }

    public void setJobStartDate(Date jobStartDate) {
        this.jobStartDate = jobStartDate;
    }

    public Date getJobFinishDate() {
        return jobFinishDate;
    }

    public void setJobFinishDate(Date jobFinishDate) {
        this.jobFinishDate = jobFinishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(String skill) {
        skills.add(skill);
    }

    public boolean isActivation() {
        return activation;
    }
    public void setActivation(boolean activation) {
        this.activation = activation;
    }

    public String getJobNotification() {
        return jobNotification.name().toLowerCase();
    }

    public void setJobNotification(String notificationJobStr){
        switch (notificationJobStr) {
            case "me" -> this.jobNotification = notification.ME;
            case "contacts" -> this.jobNotification = notification.CONTACTS;
            case "everyone" -> this.jobNotification = notification.EVERYONE;
        }
    }

}

