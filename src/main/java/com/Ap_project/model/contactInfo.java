package com.Ap_project.model;

import java.util.Date;

public class contactInfo extends Date{

    private String profileURL;
    private String shareEmail;
    private String phoneNumber;

    /**
    * *Home || work || mobile
    */
    private String numberType;
    private Date birthdate;

    /**
     * your connection || network || everyone
     */
    private String BirthdayPolicy;

    private String instantMassaging;

    public contactInfo(String profileURL, String shareEmail, String phoneNumber, String numberType, Date birthdate, String birthdayPolicy, String instantMassaging) {
        this.profileURL = profileURL;
        this.shareEmail = shareEmail;
        this.phoneNumber = phoneNumber;
        this.numberType = numberType;
        this.birthdate = birthdate;
        BirthdayPolicy = birthdayPolicy;
        this.instantMassaging = instantMassaging;
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

    public String getNumberType() {
        return numberType;
    }

    public void setNumberType(String numberType) {
        this.numberType = numberType;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthdayPolicy() {
        return BirthdayPolicy;
    }

    public void setBirthdayPolicy(String birthdayPolicy) {
        BirthdayPolicy = birthdayPolicy;
    }

    public String getInstantMassaging() {
        return instantMassaging;
    }

    public void setInstantMassaging(String instantMassaging) {
        this.instantMassaging = instantMassaging;
    }
}
