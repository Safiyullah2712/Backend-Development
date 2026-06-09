package com.student.management.model;

import java.time.LocalDateTime;

public class RefreshToken {

    private String token;
    private String username;
    private LocalDateTime expiryDate;

    public RefreshToken() {
    }

    public RefreshToken(
            String token,
            String username,
            LocalDateTime expiryDate) {

        this.token = token;
        this.username = username;
        this.expiryDate = expiryDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(
            String username) {
        this.username = username;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(
            LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}