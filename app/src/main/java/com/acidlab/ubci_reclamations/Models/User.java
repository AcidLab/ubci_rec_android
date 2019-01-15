package com.acidlab.ubci_reclamations.Models;


import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;


public class User implements Serializable {

    private int id;

    private String fname;

    private String lname;

    private String email;

    private int role;

    static Context context;



    public User() {
    }

    public User(int id, String fname, String lname, String email) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }
// Getter Methods

    public int getId() {
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

    public int getRole() {
        return role;
    }



    // Setter Methods

    public void setId(int id) {
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

    public void setRole(int role) {
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

    public void saveUser (Context context) {

        SharedPreferences sharedPref = context.getSharedPreferences("login_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("id",id);
        editor.putString("fname",fname);
        editor.putString("lname",lname);
        editor.putString("email",email);
        editor.apply();

    }
    public static void deleteUser (Context context) {

        SharedPreferences settings = context.getSharedPreferences("login_data", Context.MODE_PRIVATE);
        settings.edit().remove("fname").apply();
        settings.edit().remove("lname").apply();
        settings.edit().remove("email").apply();
        settings.edit().remove("id").apply();

    }


    public static User getCurrentUser (Context context) {

        SharedPreferences sharedPref = context.getSharedPreferences("login_data", Context.MODE_PRIVATE);

        int id = sharedPref.getInt("id", 0);
        String fname = sharedPref.getString("fname", null);
        String lname = sharedPref.getString("lname", null);
        String email = sharedPref.getString("email", null);

        User u = new User(id,fname,lname,email);

        if (email == null) {

            return null;

        }

        else {

            return u;

        }


    }
}
