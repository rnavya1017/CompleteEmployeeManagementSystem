package com.example.CompleteEmpManagementSystem.dto;

public class StatusUpdateRequest {

    private boolean enabled;


    public StatusUpdateRequest() {
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}