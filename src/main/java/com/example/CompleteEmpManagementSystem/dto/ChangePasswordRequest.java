package com.example.CompleteEmpManagementSystem.dto;

/**
 * Request object used when an authenticated user
 * wants to change their password.
 */
public class ChangePasswordRequest {

    private String currentPassword;

    private String newPassword;


    public ChangePasswordRequest() {
    }


    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }


    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}