package com.Ap_project.controller;

import com.Ap_project.DAO.EducationDAO;
import com.Ap_project.model.Education;
import com.Ap_project.model.Skill;
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

    public void creatEducation(String Id, String instituteName, String fieldOfStudy, Date educationStartDate, Date educationFinishDate, float grade, String educationalActivitiesDescription, String educationalDescription, Skill educationalSkills, String notificationEduStr) throws SQLException {
        Education education = new Education(Id, instituteName, fieldOfStudy, educationStartDate, educationFinishDate, grade, educationalActivitiesDescription, educationalDescription, educationalSkills, notificationEduStr);
        educationDAO.saveEducation(education);
    }

    public void updateEducation(String Id, String instituteName, String fieldOfStudy, Date educationStartDate, Date educationFinishDate, float grade, String educationalActivitiesDescription, String educationalDescription, Skill educationalSkills, String notificationEduStr) throws SQLException {
        Education education = new Education(Id, instituteName, fieldOfStudy, educationStartDate, educationFinishDate, grade, educationalActivitiesDescription, educationalDescription, educationalSkills, notificationEduStr);
        educationDAO.updateEducation(education);
    }

    public void deleteEducationByID(Education education) throws SQLException {
        educationDAO.deleteEducation(education);
    }

    public void deleteAllEducations() throws SQLException {
        educationDAO.deleteEducations();
    }

    public String getEducationByID(String ID) throws SQLException, JsonProcessingException {
        Education education = educationDAO.getEducation(ID);
        if (education == null) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(education);
    }

    public String getEducations() throws SQLException, JsonProcessingException{
        List<Education> educations = educationDAO.getEducations();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(educations);
    }

}
