package com.Server.DAO;

import com.Server.model.Education;

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


    public void createEducationTable() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS educations (id VARCHAR(255) PRIMARY KEY, institute VARCHAR (255) , field_study VARCHAR (255) , start_date DATE , finish_date DATE , grade FLOAT , activity_description VARCHAR (255) , education_description VARCHAR (255))");
        statement.executeUpdate();
        statement.executeUpdate();
    }

    public void saveEducation(Education education) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("INSERT INTO educations (id, institute, field_study, start_date, finish_date, grade, activity_descreption, education_description) VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
        statement.setString(1, education.getId());
        statement.setString(2, education.getInstituteName());
        statement.setString(3, education.getFieldOfStudy());
        statement.setString(4, education.getEducationStartDate());
        statement.setString(5, education.getEducationFinishDate());
        statement.setFloat(6, education.getGrade());
        statement.setString(7, education.getEducationalActivitiesDescription());
        statement.setString(8, education.getEducationalDescription());
        statement.executeUpdate();
        statement.executeUpdate();
    }

    public void deleteEducation( String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM educations WHERE id = ?");
        statement.setString(1, id);
        statement.executeUpdate();
    }

    public void deleteEducations() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM educations");
        statement.executeUpdate();
    }

    public void deleteEducation(String id, String institute) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM educations WHERE id = ? AND institute = ?");

        statement.setString(1, id);
        statement.setString(2, institute);

        statement.executeUpdate();
    }



    public void updateEducation(Education education, String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE educations SET institute = ?, field_study = ?, start_date = ?, finish_date = ?, grade = ?, activity_descreption = ?, education_description = ? WHERE id = ?");
        statement.setString(1, education.getInstituteName());
        statement.setString(2, education.getFieldOfStudy());
        statement.setString(3, education.getEducationStartDate());
        statement.setString(4, education.getEducationFinishDate());
        statement.setFloat(5, education.getGrade());
        statement.setString(6, education.getEducationalActivitiesDescription());
        statement.setString(7, education.getEducationalDescription());
        statement.setString(8, id);


        statement.executeUpdate();
    }

    public Education getEducationsbyid(String userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM educations WHERE id = ?");
        statement.setString(1, userId);
        ResultSet resultSet = statement.executeQuery();

            Education education = new Education();
        if (resultSet.next()) {
            education.setId(resultSet.getString("id"));
            education.setInstituteName(resultSet.getString("institute"));
            education.setFieldOfStudy(resultSet.getString("field_study"));
            education.setEducationStartDate(resultSet.getString("start_date"));
            education.setEducationFinishDate(resultSet.getString("finish_date"));
            education.setGrade(resultSet.getFloat("grade"));
            education.setEducationalActivitiesDescription(resultSet.getString("activity_description"));
            education.setEducationalDescription(resultSet.getString("education_description"));

            System.out.println(education.toString() + "123");
            return education;
        }

        return null;
    }

    public Education getEducation(String userId , String institute) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM educations WHERE id = ? AND institute = ?");
        statement.setString(1, userId);
        statement.setString(2, institute);
        ResultSet resultSet = statement.executeQuery();
        Education education = new Education();

        if (resultSet.next()) {
            education.setId(resultSet.getString("id"));
            education.setInstituteName(resultSet.getString("institute"));
            education.setFieldOfStudy(resultSet.getString("field_study"));
            education.setEducationStartDate(resultSet.getString("start_date"));
            education.setEducationFinishDate(resultSet.getString("finish_date"));
            education.setGrade(resultSet.getFloat("grade"));
            education.setEducationalActivitiesDescription(resultSet.getString("activity_description"));
            education.setEducationalDescription(resultSet.getString("education_description"));


            return education;
        }

        return null;
    }

    public ArrayList<Education> getEducations() throws SQLException { //ALL IN UNIVERSAL
        ArrayList<Education> educations = new ArrayList<Education>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM educations");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Education education = new Education();
            education.setId(resultSet.getString("id"));
            education.setInstituteName(resultSet.getString("institute"));
            education.setFieldOfStudy(resultSet.getString("field_study"));
            education.setEducationStartDate(resultSet.getString("start_date"));
            education.setEducationFinishDate(resultSet.getString("finish_date"));
            education.setGrade(resultSet.getFloat("grade"));
            education.setEducationalActivitiesDescription(resultSet.getString("activity_description"));
            education.setEducationalDescription(resultSet.getString("education_description"));


            educations.add(education);
        }

        return educations;
    }

    public ArrayList<Education> getEducations(String id) throws SQLException { // ALL OF A PERSON
        ArrayList<Education> educations = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM educations WHERE id = ?");
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();
        Education education = new Education();
        while (resultSet.next()) {

            education.setId(resultSet.getString("id"));
            education.setInstituteName(resultSet.getString("institute"));
            education.setFieldOfStudy(resultSet.getString("field_study"));
            education.setEducationStartDate(resultSet.getString("start_date"));
            education.setEducationFinishDate(resultSet.getString("finish_date"));
            education.setGrade(resultSet.getFloat("grade"));
            education.setEducationalActivitiesDescription(resultSet.getString("activity_description"));
            education.setEducationalDescription(resultSet.getString("education_description"));

            educations.add(education);
        }

        return educations;
    }
}
