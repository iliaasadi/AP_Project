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

    /**
     * sth like instagram / telegram / ...
     */
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

    public contactInfo(){

    }

}
