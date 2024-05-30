package com.Ap_project.DAO;


import com.Ap_project.model.ContactInfo;

import java.sql.*;
import java.util.ArrayList;

public class ContactInfoDAO {
    private final Connection connection;
    public ContactInfoDAO() throws SQLException {
        connection = DataBase.getConnection();
        creatContactInfoTable();
    }

    private void creatContactInfoTable() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS contacts (id VARCHAR (255) PRIMARY KEY, profile_url VARCHAR (255) , email VARCHAR (255) , phone_number VARCHAR (255) , phone_type VARCHAR (255) , birth_date DATE , birthday_policy VARCHAR (255),address VARCHAR (255) , instant_message VARCHAR (255))");
        statement.executeUpdate();
        statement.executeUpdate();
    }

    private void saveContact(ContactInfo contact) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("INSERT INTO contacs (id , profile_url , email , phone_number , phone_type ,birth_date , birthday_policy , address , instant_message ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, contact.getID());
        statement.setString(2, contact.getProfileURL());
        statement.setString(3, contact.getShareEmail());
        statement.setString(4, contact.getPhoneNumber());
        statement.setString(5, contact.getNumberType());
        statement.setDate(6, contact.getBirthdate());
        statement.setString(7, contact.getBirthdayPolicy());
        statement.setString(8, contact.getAddress());
        statement.setString(9, contact.getInstantMassaging());

        statement.executeUpdate();
    }

    public void deleteContact(String userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM contacts WHERE id = ?");
        statement.setString(1, userId);
        statement.executeUpdate();
    }

    public void deleteContacts() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM contacts");
        statement.executeUpdate();
    }

    public void updateContacts(ContactInfo contact) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE contacts SET email = ? , phone_nuber = ? , phone_type = ? , birth_date = ? , birthday_policy = ? , address = ? , instant_message = ? WHERE user_id = ?");
        statement.setString(1, contact.getShareEmail());
        statement.setString(2, contact.getPhoneNumber());
        statement.setString(3, contact.getNumberType());
        statement.setDate(4, contact.getBirthdate());
        statement.setString(5, contact.getBirthdayPolicy());
        statement.setString(6, contact.getAddress());
        statement.setString(7, contact.getInstantMassaging());
        statement.executeUpdate();
    }

    public ContactInfo getContact(String userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM contacts WHERE user_id = ?");
        statement.setString(1, userId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            ContactInfo contact = new ContactInfo();
            contact.setID(resultSet.getString("id"));
            contact.setProfileURL(resultSet.getString("profile_url"));
            contact.setShareEmail(resultSet.getString("email"));
            contact.setPhoneNumber(resultSet.getString("phone_number"));
            contact.setNumberType(resultSet.getString("phone_type"));
            contact.setBirthdate(resultSet.getDate("birth_day"));
            contact.setBirthdayPolicy(resultSet.getString("birthday_policy"));
            contact.setAddress(resultSet.getString("address"));
            contact.setInstantMassaging(resultSet.getString("instant_message"));
            return contact;
        }

        return null;
    }

    public ArrayList<ContactInfo> getContacts() throws SQLException {
        ArrayList<ContactInfo> contacts = new ArrayList<ContactInfo>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM contacts");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            ContactInfo contact = new ContactInfo();
            contact.setID(resultSet.getString("id"));
            contact.setProfileURL(resultSet.getString("profile_url"));
            contact.setShareEmail(resultSet.getString("email"));
            contact.setPhoneNumber(resultSet.getString("phone_number"));
            contact.setNumberType(resultSet.getString("phone_type"));
            contact.setBirthdate(resultSet.getDate("birth_day"));
            contact.setBirthdayPolicy(resultSet.getString("birthday_policy"));
            contact.setAddress(resultSet.getString("address"));
            contact.setInstantMassaging(resultSet.getString("instant_message"));
            contacts.add(contact);
        }

        return contacts;
    }

}
