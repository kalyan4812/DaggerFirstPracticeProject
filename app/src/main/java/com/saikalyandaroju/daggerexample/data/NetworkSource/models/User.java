package com.saikalyandaroju.daggerexample.data.NetworkSource.models;

public class User {
    private int id;
    private String email,website,username;

    public User(){

    }
    public User(int id, String email, String website, String username) {
        this.id = id;
        this.email = email;
        this.website = website;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
