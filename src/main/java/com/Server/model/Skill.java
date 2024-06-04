package com.Server.model;

public class Skill {
    private String userID;
    private String skill1 = null;
    private String skill2 = null;
    private String skill3 = null;
    private String skill4 = null;
    private String skill5 = null;

    public Skill(String userID) {
        this.userID = userID;
    }

    public Skill(String userID, String skill1) {
        this.userID = userID;
        this.skill1 = skill1;
    }

    public Skill(String userID, String skill1, String skill2) {
        this.userID = userID;
        this.skill1 = skill1;
        this.skill2 = skill2;
    }

    public Skill(String userID, String skill1, String skill2, String skill3) {
        this.userID = userID;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
    }

    public Skill(String userID, String skill1, String skill2, String skill3, String skill4) {
        this.userID = userID;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.skill4 = skill4;
    }

    public Skill(String userID, String skill1, String skill2, String skill3, String skill4, String skill5) {
        this.userID = userID;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.skill4 = skill4;
        this.skill5 = skill5;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getSkill4() {
        return skill4;
    }

    public void setSkill4(String skill4) {
        this.skill4 = skill4;
    }

    public String getSkill5() {
        return skill5;
    }

    public void setSkill5(String skill5) {
        this.skill5 = skill5;
    }
}
