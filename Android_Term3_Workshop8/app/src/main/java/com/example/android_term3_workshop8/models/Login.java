/*
Author - Rohit
Android - Term 3 Project
 */

package com.example.android_term3_workshop8.models;

public class Login {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void getCredentials(){

    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
