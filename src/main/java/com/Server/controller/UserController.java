package com.Server.controller;

import com.Server.DAO.ContactDAO;
import com.Server.DAO.UserDAO;
import com.Server.model.Contact;
import com.Server.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
    private final ContactDAO contactDAO;
    private final UserDAO userDAO;
    public UserController() throws SQLException {
        contactDAO = new ContactDAO();
        userDAO = new UserDAO();
    }

    public void createUser(String id, String email, String firstName, String lastName, String passWord,String additionalName, String joinDate, String workType) throws SQLException {
        User user = new User(id, email , firstName , lastName , passWord ,additionalName, joinDate , workType );
        if (isUserExists(id))
            userDAO.updateUser(user);
        else
            userDAO.saveUser(user);
    }

    public void creatContact(String userId, String profileURL, String shareEmail, String phoneNumber, String numberType, String birthdate, String address, String birthdayPolicy, String instantMassaging) throws SQLException {
        Contact contact = new Contact(userId , profileURL , shareEmail , phoneNumber , numberType , birthdate , address,birthdayPolicy , instantMassaging);
        if (userDAO.getUser(userId) == null) throw new SQLException("User does not exist");
        if (contactDAO.getContact(userId) == null) {
            contactDAO.saveContact(contact);
        } else
            contactDAO.updateContacts(contact);
    }

    public void deleteUser(String id) throws SQLException {
        User user = new User();
        user.setID(id);
        userDAO.deleteUser(user);
    }

    public void deleteBio(String userId) throws SQLException {
        contactDAO.deleteContact(userId);
    }

    public void deleteUsers() throws SQLException {
        userDAO.deleteUsers();
    }

    public void deleteBios() throws SQLException {
        contactDAO.deleteContacts();
    }

    public void updateUser(String id, String email, String firstName, String lastName, String passWord,String additionalName, String joinDate, String workType) throws SQLException {
        User user = new User(id, email, firstName, lastName, passWord,additionalName, joinDate, workType);
        userDAO.updateUser(user);
    }

    public void updateContact(String userId, String profileURL, String shareEmail, String phoneNumber, String numberType, String birthdate, String address, String birthdayPolicy, String instantMassaging) throws SQLException {
        Contact contact = new Contact(userId, profileURL, shareEmail, phoneNumber, numberType, birthdate, address, birthdayPolicy, instantMassaging);
        contactDAO.updateContacts(contact);
    }

    public boolean isUserExists(String ID) {
        if (ID == null) return false;
        try {
            return (userDAO.getUser(ID) != null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *
     * json
     * @param id
     * @return
     * @throws SQLException
     */
    public String getUserById(String id) throws SQLException, JsonProcessingException {
        User user = userDAO.getUser(id);
        if (user == null) return null;

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(user);
    }


    /**
     *
     * json
     * @param userId
     * @return
     * @throws SQLException
     */
    public String getContactByUserId(String userId) throws SQLException, JsonProcessingException {
        Contact contact = contactDAO.getContact(userId);
        if (contact == null) return null;
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(contact);
    }

    /**
     *
     * json
     * @return
     * @throws SQLException
     */

    public String getUsers() throws SQLException, JsonProcessingException {
        ArrayList<User> users = userDAO.getUsers();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(users);
    }


    /**
     *
     * json
     * @return
     * @throws SQLException
     */
    public String getContacts() throws SQLException, JsonProcessingException {
        ArrayList<Contact> contacts = contactDAO.getContacts();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(contacts);
    }

    /**
     * json
     * @param id
     * @param pass
     * @return
     * @throws SQLException
     */
    public String getUserByIdAndPass(String id, String pass) throws SQLException, JsonProcessingException {
        User user = userDAO.getUser(id, pass);
        if (user == null) return null;
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(user);
    }
    public String getUserByEmailAndPass(String email, String pass) throws SQLException, JsonProcessingException {
        User user = userDAO.getUserByEmail(email, pass);
        if (user == null) return null;
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(user);
    }

    public boolean isUserExist(String id) throws SQLException {
        return userDAO.isUserExist(id);
    }
    public boolean isEmailExist(String email) throws SQLException {
        return userDAO.isUserExistByEmail(email);
    }


}
