package com.acidlab.ubci_reclamations.Models;


import java.io.Serializable;


public class User implements Serializable {

    private float id;

    private String fname;

    private String lname;

    private String email;

    private float role;


    public User() {
    }

    public User(float id, String fname, String lname, String email) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }
// Getter Methods

    public float getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public float getRole() {
        return role;
    }



    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(float role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
