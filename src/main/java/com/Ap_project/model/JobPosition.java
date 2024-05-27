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

    public JobPosition(){

    }
}

