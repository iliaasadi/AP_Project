package com.Server.DAO;


import com.Server.model.Contact;

import java.sql.*;
import java.util.ArrayList;

public class ContactDAO {
    private final Connection connection;
    public ContactDAO() throws SQLException {
        connection = DataBase.getConnection();
        creatContactTable();
    }

    public void creatContactTable() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS contacts (id VARCHAR (255) PRIMARY KEY, profile_url VARCHAR (255) , email VARCHAR (255) , phone_number VARCHAR (255) , phone_type VARCHAR (255) , birth_date DATE , birthday_policy VARCHAR (255),address VARCHAR (255) , instant_message VARCHAR (255))");
        statement.executeUpdate();
        statement.executeUpdate();
    }

    public void saveContact(Contact contact) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("INSERT INTO contacts (id , profile_url , email , phone_number , phone_type ,birth_date , birthday_policy , address , instant_message ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, contact.getID());
        statement.setString(2, contact.getProfileURL());
        statement.setString(3, contact.getShareEmail());
        statement.setString(4, contact.getPhoneNumber());
        statement.setString(5, contact.getNumberType());
        statement.setString(6, contact.getBirthdate());
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

    public void updateContacts(Contact contact) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE contacts SET email = ? , phone_number = ? , phone_type = ? , birth_date = ? , birthday_policy = ? , address = ? , instant_message = ? WHERE id = ?");
        statement.setString(1, contact.getShareEmail());
        statement.setString(2, contact.getPhoneNumber());
        statement.setString(3, contact.getNumberType());
        statement.setString(4, contact.getBirthdate());
        statement.setString(5, contact.getBirthdayPolicy());
        statement.setString(6, contact.getAddress());
        statement.setString(7, contact.getInstantMassaging());
        statement.setString(8, contact.getID());
        statement.executeUpdate();
    }

    public Contact getContact(String userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM contacts WHERE id = ?");
        statement.setString(1, userId);
        ResultSet resultSet = statement.executeQuery();

            Contact contact = new Contact();
        if (resultSet.next()) {
            contact.setID(resultSet.getString("id"));
            contact.setProfileURL(resultSet.getString("profile_url"));
            contact.setShareEmail(resultSet.getString("email"));
            contact.setPhoneNumber(resultSet.getString("phone_number"));
            contact.setNumberType(resultSet.getString("phone_type"));
            contact.setBirthdate(resultSet.getString("birth_date"));
            contact.setBirthdayPolicy(resultSet.getString("birthday_policy"));
            contact.setAddress(resultSet.getString("address"));
            contact.setInstantMassaging(resultSet.getString("instant_message"));
            return contact;
        }

        return null;
    }

    public ArrayList<Contact> getContacts() throws SQLException {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM contacts");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Contact contact = new Contact();
            contact.setID(resultSet.getString("id"));
            contact.setProfileURL(resultSet.getString("profile_url"));
            contact.setShareEmail(resultSet.getString("email"));
            contact.setPhoneNumber(resultSet.getString("phone_number"));
            contact.setNumberType(resultSet.getString("phone_type"));
            contact.setBirthdate(resultSet.getString("birth_date"));
            contact.setBirthdayPolicy(resultSet.getString("birthday_policy"));
            contact.setAddress(resultSet.getString("address"));
            contact.setInstantMassaging(resultSet.getString("instant_message"));
            contacts.add(contact);
        }

        return contacts;
    }

}
