package com.rental;

public class User {
    private String fname;
    private String lname;
    private String phoneNumber;

    // Constructors
    public User() {}

    public User(String fname, String lname, String phoneNumber) {
        this.fname = fname;
        this.lname = lname;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
