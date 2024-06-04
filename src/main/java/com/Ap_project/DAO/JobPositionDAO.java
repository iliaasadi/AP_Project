package com.Ap_project.DAO;

import com.Ap_project.model.JobPosition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class JobPositionDAO {
    private final Connection connection;

    public JobPositionDAO() throws SQLException {
        connection = DataBase.getConnection();
        createJobPositionTable();
    }

    /**
     * check class """"skill"""" and relate it here !!!!!!!!!!!!!!!!!!!
     *
     * @throws SQLException
     */
    public void createJobPositionTable() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS job_position (id VARCHAR(255) PRIMARY KEY, title VARCHAR (255) , job_type VARCHAR (255) , company_name VARCHAR(255) , company_address VARCHAR(255) , company_type VARCHAR(255) ,occupation_status VARCHAR(255) ,job_start_date DATE,job_finish_date DATE,description VARCHAR (255))");
        statement.executeUpdate();
    }

    public void saveJobPosition(JobPosition jobPosition) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO job_position (id, title, job_type, company_name, company_address, company_type, occupation_status, job_start_date, job_finish_date, description,job_notification) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
        statement.setString(1, jobPosition.getId());
        statement.setString(2, jobPosition.getTitle());
        statement.setString(3, jobPosition.getJobType());
        statement.setString(4, jobPosition.getCompanyName());
        statement.setString(5, jobPosition.getCompanyAddress());
        statement.setString(6, jobPosition.getCompanyType());
        statement.setString(7, jobPosition.getOccupationStatus());
        statement.setDate(8, jobPosition.getJobStartDate());
        statement.setDate(9, jobPosition.getJobFinishDate());
        statement.setString(10, jobPosition.getDescription());
        statement.setString(11, jobPosition.getJobNotification());

        statement.executeUpdate();
    }

    public void deleteJobPosition(JobPosition jobPosition) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM job_position WHERE id = ?");
        statement.setString(1, jobPosition.getId());
        statement.executeUpdate();
    }

    public void deleteUsers() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM job_position");
        statement.executeUpdate();
    }

    public void updateJobPosition(JobPosition jobPosition) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE job_position SET title = ?, job_type = ?, company_name = ?, company_address = ?, company_type = ?,occupation_status = ?,job_start_date = ?,job_finish_date = ?, description = ?,job_notification =? WHERE id = ?");

        statement.setString(1, jobPosition.getTitle());
        statement.setString(2, jobPosition.getJobType());
        statement.setString(3, jobPosition.getCompanyName());
        statement.setString(4, jobPosition.getCompanyAddress());
        statement.setString(5, jobPosition.getCompanyType());
        statement.setString(6, jobPosition.getOccupationStatus());
        statement.setDate(7, jobPosition.getJobStartDate());
        statement.setDate(8, jobPosition.getJobFinishDate());
        statement.setString(9, jobPosition.getDescription());
        statement.setString(10, jobPosition.getJobNotification());
        statement.setString(11, jobPosition.getId());

        statement.executeUpdate();
    }

    public JobPosition getJobPosition(String userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM job_position WHERE id = ?");
        statement.setString(1, userId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            JobPosition jobPosition = new JobPosition();
            jobPosition.setId(resultSet.getString("id"));
            jobPosition.setTitle(resultSet.getString("title"));
            jobPosition.setJobType(resultSet.getString("job_type"));
            jobPosition.setCompanyName(resultSet.getString("company_name"));
            jobPosition.setCompanyAddress(resultSet.getString("company_address"));
            jobPosition.setCompanyType(resultSet.getString("company_type"));
            jobPosition.setOccupationStatus(resultSet.getString("occupation_status"));
            jobPosition.setJobStartDate(resultSet.getDate("job_start_date"));
            jobPosition.setJobFinishDate(resultSet.getDate("job_finish_date"));
            jobPosition.setDescription(resultSet.getString("description"));
            jobPosition.setJobNotification(resultSet.getString("job_notification"));

            return jobPosition;
        }

        return null;
    }

    public ArrayList<JobPosition> getJobPosition() throws SQLException {
        ArrayList<JobPosition> jobPositions = new ArrayList<JobPosition>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM job_position");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            JobPosition jobPosition = new JobPosition();
            jobPosition.setId(resultSet.getString("id"));
            jobPosition.setTitle(resultSet.getString("title"));
            jobPosition.setJobType(resultSet.getString("job_type"));
            jobPosition.setCompanyName(resultSet.getString("company_name"));
            jobPosition.setCompanyAddress(resultSet.getString("company_address"));
            jobPosition.setCompanyType(resultSet.getString("company_type"));
            jobPosition.setOccupationStatus(resultSet.getString("occupation_status"));
            jobPosition.setJobStartDate(resultSet.getDate("job_start_date"));
            jobPosition.setJobFinishDate(resultSet.getDate("job_finish_date"));
            jobPosition.setDescription(resultSet.getString("description"));
            jobPosition.setJobNotification(resultSet.getString("job_notification"));


            jobPositions.add(jobPosition);
        }

        return jobPositions;
    }
}
