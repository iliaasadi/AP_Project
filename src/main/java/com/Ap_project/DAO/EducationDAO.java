package com.Ap_project.DAO;

import com.Ap_project.model.Education;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EducationDAO {
    private final Connection connection;
    public EducationDAO() throws SQLException {
        connection = DataBase.getConnection();
        createEducationTable();
    }
    /**
     * check class """"skill"""" and relate it here !!!!!!!!!!!!!!!!!!!
     *
     * @throws SQLException
     */
    public void createEducationTable() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS educations (id VARCHAR(255) PRIMARY KEY, institute VARCHAR (255) , field_study VARCHAR (255) , start_date DATE , finish_date DATE , grade FLOAT , activity_description VARCHAR (255) , education_description VARCHAR (255))");
        statement.executeUpdate();
    }

    public void saveEducation(Education education) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO educations (id, institute, field_study, start_date, finish_date, grade, activity_descreption, education_description, edu_notification) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,?)");
        statement.setString(1, education.getId());
        statement.setString(2, education.getInstituteName());
        statement.setString(3, education.getFieldOfStudy());
        statement.setDate(4, education.getEducationStartDate());
        statement.setDate(5, education.getEducationFinishDate());
        statement.setFloat(6, education.getGrade());
        statement.setString(7, education.getEducationalActivitiesDescription());
        statement.setString(8, education.getEducationalDescription());
        statement.setString(9,education.getEduNotification());

        statement.executeUpdate();
    }

    public void deleteEducation(Education education) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM educations WHERE id = ?");
        statement.setString(1, education.getId());
        statement.executeUpdate();
    }

    public void deleteUsers() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM educations");
        statement.executeUpdate();
    }

    public void updateEducation(Education education) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE educations SET institute = ?, field_study = ?, start_date = ?, finish_date = ?, grade = ?, activity_descreption = ?, education_description = ?,edu_notification =? WHERE id = ?");
        statement.setString(1, education.getInstituteName());
        statement.setString(2, education.getFieldOfStudy());
        statement.setDate(3, education.getEducationStartDate());
        statement.setDate(4, education.getEducationFinishDate());
        statement.setFloat(5, education.getGrade());
        statement.setString(6, education.getEducationalActivitiesDescription());
        statement.setString(7, education.getEducationalDescription());
        statement.setString(8,education.getEduNotification());
        statement.setString(9,education.getId());


        statement.executeUpdate();
    }

    public Education getEducation(String userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM educations WHERE id = ?");
        statement.setString(1, userId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Education education = new Education();
            education.setId(resultSet.getString("id"));
            education.setInstituteName(resultSet.getString("institute"));
            education.setFieldOfStudy(resultSet.getString("field_study"));
            education.setEducationStartDate(resultSet.getDate("start_date"));
            education.setEducationFinishDate(resultSet.getDate("finish_date"));
            education.setGrade(resultSet.getFloat("grade"));
            education.setEducationalActivitiesDescription(resultSet.getString("activity_description"));
            education.setEducationalDescription(resultSet.getString("education_description"));
            education.setEduNotification("edu_notification");

            return education;
        }

        return null;
    }

    public ArrayList<Education> getEducations() throws SQLException {
        ArrayList<Education> educations = new ArrayList<Education>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM educations");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Education education = new Education();
            education.setId(resultSet.getString("id"));
            education.setInstituteName(resultSet.getString("institute"));
            education.setFieldOfStudy(resultSet.getString("field_study"));
            education.setEducationStartDate(resultSet.getDate("start_date"));
            education.setEducationFinishDate(resultSet.getDate("finish_date"));
            education.setGrade(resultSet.getFloat("grade"));
            education.setEducationalActivitiesDescription(resultSet.getString("activity_description"));
            education.setEducationalDescription(resultSet.getString("education_description"));
            education.setEduNotification(resultSet.getString("edu_notification"));

            educations.add(education);
        }

        return educations;
    }
}
