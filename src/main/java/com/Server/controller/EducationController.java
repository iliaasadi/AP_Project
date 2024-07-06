package com.Server.controller;

import com.Server.DAO.EducationDAO;
import com.Server.model.Contact;
import com.Server.model.Education;
import com.Server.model.Skill;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EducationController {
    private final EducationDAO educationDAO;

    public EducationController() throws SQLException {
        this.educationDAO = new EducationDAO();
    }

    public void creatEducation(String Id, String instituteName, String fieldOfStudy, String educationStartDate, String educationFinishDate, float grade, String educationalActivitiesDescription, String educationalDescription) throws SQLException {
        Education education = new Education(Id, instituteName, fieldOfStudy, educationStartDate, educationFinishDate, grade, educationalActivitiesDescription, educationalDescription);
        educationDAO.saveEducation(education);
    }

    public void updateEducation(String Id, String instituteName, String fieldOfStudy, String educationStartDate, String educationFinishDate, float grade, String educationalActivitiesDescription, String educationalDescription) throws SQLException {
        Education education = new Education(Id, instituteName, fieldOfStudy, educationStartDate, educationFinishDate, grade, educationalActivitiesDescription, educationalDescription);
        educationDAO.updateEducation(education, Id);
    }

    public void deleteEducationByID(String id) throws SQLException {
        educationDAO.deleteEducation(id);
    }

    public String deleteEducationByIDAndInstitute(String id, String institute) throws SQLException, JsonProcessingException {
        Education education = educationDAO.getEducation(id, institute);
        if (education == null) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(education);
    }

    public void deleteAllEducations() throws SQLException {
        educationDAO.deleteEducations();
    }

    public String getEducations(String id) throws SQLException, JsonProcessingException {
        Education education = educationDAO.getEducationsbyid(id);
        if(education == null)return null;
        System.out.println("dao ok");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(education);
    }
    public String getEducationByIDAndInstitute(String id, String institute) throws SQLException, JsonProcessingException {
        Education education = educationDAO.getEducation(id, institute);
        if (education  == null) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(education);
    }

    public String getEducations() throws SQLException, JsonProcessingException {
        List<Education> educations = educationDAO.getEducations();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(educations);
    }

}
