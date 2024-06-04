package com.Ap_project.controller;

import com.Ap_project.DAO.JobPositionDAO;
import com.Ap_project.model.JobPosition;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONMLParserConfiguration;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class JobPositionController {
    private final JobPositionDAO jobPositionDAO;

    public JobPositionController() throws SQLException {
        this.jobPositionDAO = new JobPositionDAO();
    }

    public void creatJobPosition(String Id, String title, String jobType, String companyName, String companyAddress, String companyType, String occupationStatus, Date jobStartDate, String description, String skill, String notificationJobStr) throws SQLException{
        JobPosition jobPosition = new JobPosition(Id , title , jobType , companyName , companyAddress , companyType , occupationStatus , jobStartDate , description , skill , notificationJobStr);
        jobPositionDAO.saveJobPosition(jobPosition);
    }

    public void updateJobPosition(String Id, String title, String jobType, String companyName, String companyAddress, String companyType, String occupationStatus, Date jobStartDate, String description, String skill, String notificationJobStr) throws SQLException {
        JobPosition jobPosition = new JobPosition(Id, title, jobType, companyName, companyAddress, companyType, occupationStatus, jobStartDate, description, skill, notificationJobStr);
        jobPositionDAO.updateJobPosition(jobPosition);
    }

    public void deleteJobPosition(JobPosition jobPosition)throws SQLException {
        jobPositionDAO.deleteJobPosition(jobPosition);
    }

    public void deleteAll() throws SQLException {
        jobPositionDAO.deleteAll();
    }

    public String getJobPosition(String ID) throws SQLException, JsonProcessingException {
        JobPosition jobPosition = jobPositionDAO.getJobPosition(ID);
        if(jobPosition == null)return null;
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(jobPosition);
    }

    public String getAll() throws SQLException, JsonProcessingException {
        List<JobPosition> jobPositions = jobPositionDAO.getJobPositions();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(jobPositions);
    }
}
