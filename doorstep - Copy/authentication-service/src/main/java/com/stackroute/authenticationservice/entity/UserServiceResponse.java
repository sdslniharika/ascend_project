package com.stackroute.authenticationservice.entity;

public class UserServiceResponse {

    private String emailId;
    private String password;
    private String customerId;
    private String role;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserSeerviceResponse{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", customerId='" + customerId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
