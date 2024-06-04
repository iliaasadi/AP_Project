package com.Server.model;

public class Location {
    private String userId;
    private String country;
    private String city;

    public Location() {
    }

    public Location(String userId, String country, String city) {
        this.userId = userId;
        this.country = country;
        this.city = city;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
