package com.Ap_project.model;

import java.util.ArrayList;
import java.util.Date;

public class JobPosition extends Date {
    private String title;
    private String jobType;
    private String companyName;
    private String companyAddress;
    private String companyType;
    private String occupationStatus;
    private Date  jobStartDate;
    private Date jobFinishDate;
    private String description;
    private ArrayList<String> jobSkills;
    private boolean changeNotification;

    public JobPosition(String title, String jobType, String companyName, String companyAddress, String companyType, String occupationStatus, Date jobStartDate, Date jobFinishDate, String description, ArrayList<String> jobSkills, boolean changeNotification) {
        this.title = title;
        this.jobType = jobType;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyType = companyType;
        this.occupationStatus = occupationStatus;
        this.jobStartDate = jobStartDate;
        this.jobFinishDate = jobFinishDate;
        this.description = description;
        this.jobSkills = jobSkills;
        this.changeNotification = changeNotification;
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

    public ArrayList<String> getJobSkills() {
        return jobSkills;
    }

    public void setJobSkills(ArrayList<String> jobSkills) {
        this.jobSkills = jobSkills;
    }

    public boolean isChangeNotification() {
        return changeNotification;
    }

    public void setChangeNotification(boolean changeNotification) {
        this.changeNotification = changeNotification;
    }
}

