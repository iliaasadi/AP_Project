package com.Ap_project.controller;

import com.Ap_project.DAO.ContactDAO;
import com.Ap_project.DAO.UserDAO;
import com.Ap_project.model.Contact;
import com.Ap_project.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
    private final ContactDAO contactDAO;
    private final UserDAO userDAO;

    /**
     *
     *
     *
     *
     *
     *
     *
     * اینحا از جیسون استفاده کردم تو این کلاس سینا اینا
     * چون هموز بلد نیستم اون جاهایی که جیسون لازم بود رو یکم با تقییز زدم
     * بالای اوما کامنت میزارم
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * @throws SQLException
     */
    public UserController() throws SQLException {
        contactDAO = new ContactDAO();
        userDAO = new UserDAO();
    }

    public void createUser(String id, String email, String firstName, String lastName, String passWord, Date joinDate, String workType) throws SQLException {
        User user = new User(id, email , firstName , lastName , passWord , joinDate , workType );
        if (isUserExists(id))
            userDAO.updateUser(user);
        else
            userDAO.saveUser(user);
    }

    public void creatContact(String userId, String profileURL, String shareEmail, String phoneNumber, String numberType, Date birthdate, String address, String birthdayPolicy, String instantMassaging) throws SQLException {
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

    public void updateUser(String id, String email, String firstName, String lastName, String passWord, Date joinDate, String workType) throws SQLException {
        User user = new User(id, email, firstName, lastName, passWord, joinDate, workType);
        userDAO.updateUser(user);
    }

    public void updateContact(String userId, String profileURL, String shareEmail, String phoneNumber, String numberType, Date birthdate, String address, String birthdayPolicy, String instantMassaging) throws SQLException {
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
    public String getBioByUserId(String userId) throws SQLException, JsonProcessingException {
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


}
