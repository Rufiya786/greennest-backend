package com.greennest.backend.dto;

public class AdminLoginRequest {

    private String email;
    private String password;

    public AdminLoginRequest() {
        // needed so Jackson can create this object from JSON
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
