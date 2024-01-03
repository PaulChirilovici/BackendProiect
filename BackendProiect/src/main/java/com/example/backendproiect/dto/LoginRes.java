package com.example.backendproiect.dto;

public class LoginRes {
    private String email;


    public LoginRes(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
