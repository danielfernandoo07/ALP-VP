package com.example.vp_alpapp.model;

public class Login {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
