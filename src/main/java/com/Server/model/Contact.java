package com.Server.model;


import java.sql.Date;

public class Contact {

    private String ID;
    private String profileURL;
    private String shareEmail;
    private String phoneNumber;

    enum phoneType {
        MOBILE,
        HOME,
        WORK
    }



    private phoneType numberType;
    private Date birthdate;
    private String address;

    enum notification {
        ME,
        CONTACTS,
        EVERYONE
    }

    private notification BirthdayPolicy;

    /**
     * sth like instagram / telegram / ...
     */
    private String instantMassaging;

    public Contact(String ID, String profileURL, String shareEmail, String phoneNumber, String numberType, Date birthdate, String address, String birthdayPolicy, String instantMassaging) {
        this.ID = ID;
        this.profileURL = profileURL;
        this.shareEmail = shareEmail;
        this.phoneNumber = phoneNumber;
        switch (numberType) {
            case "mobile" -> this.numberType = phoneType.MOBILE;
            case "home" -> this.numberType = phoneType.HOME;
            case "work" -> this.numberType = phoneType.WORK;
        }
        this.birthdate = birthdate;
        this.address = address;
        switch (birthdayPolicy) {
            case "me" -> this.BirthdayPolicy = notification.ME;
            case "contacts" -> this.BirthdayPolicy = notification.CONTACTS;
            case "everyone" -> this.BirthdayPolicy = notification.EVERYONE;
        }
        this.instantMassaging = instantMassaging;
    }

    public Contact() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public String getShareEmail() {
        return shareEmail;
    }

    public void setShareEmail(String shareEmail) {
        this.shareEmail = shareEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public void setNumberType(String numberType) {
        switch (numberType) {
            case "mobile" -> this.numberType = phoneType.MOBILE;
            case "home" -> this.numberType = phoneType.HOME;
            case "work" -> this.numberType = phoneType.WORK;
        }
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberType() {
        return numberType.name().toLowerCase();
    }

    public String getBirthdayPolicy() {
        return BirthdayPolicy.name().toLowerCase();
    }

    public void setBirthdayPolicy(String birthdayPolicy) {
        switch (birthdayPolicy) {
            case "me" -> this.BirthdayPolicy = notification.ME;
            case "contacts" -> this.BirthdayPolicy = notification.CONTACTS;
            case "everyone" -> this.BirthdayPolicy = notification.EVERYONE;
        }
    }

    public String getInstantMassaging() {
        return instantMassaging;
    }

    public void setInstantMassaging(String instantMassaging) {
        this.instantMassaging = instantMassaging;
    }
}
