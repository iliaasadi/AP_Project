package com.Ap_project.controller;

import com.Ap_project.DAO.ContactDAO;
import com.Ap_project.model.Contact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ContactController {
    private final ContactDAO contactDAO;

    public ContactController() throws SQLException {
        this.contactDAO = new ContactDAO();
    }

    public void creatContact(String ID, String profileURL, String shareEmail, String phoneNumber, String numberType, Date birthdate, String address, String birthdayPolicy, String instantMassaging) throws SQLException {
        Contact contact = new Contact(ID , profileURL , shareEmail , phoneNumber , numberType , birthdate , address , birthdayPolicy , instantMassaging);
        contactDAO.saveContact(contact);
    }

    public void updateContact(String ID, String profileURL, String shareEmail, String phoneNumber, String numberType, Date birthdate, String address, String birthdayPolicy, String instantMassaging) throws SQLException {
        Contact contact = new Contact(ID, profileURL, shareEmail, phoneNumber, numberType, birthdate, address, birthdayPolicy, instantMassaging);
        contactDAO.updateContacts(contact);
    }

    public void deleteContactByID(String ID) throws SQLException {
        contactDAO.deleteContact(ID);
    }

    public void deleteAll() throws SQLException {
        contactDAO.deleteContacts();
    }

    public String getContact(String ID) throws JsonProcessingException, SQLException {
        Contact contact = contactDAO.getContact(ID);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(contact);
    }

    public String getAll() throws JsonProcessingException, SQLException {
        List<Contact> contacts = contactDAO.getContacts();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(contacts);
    }

}


