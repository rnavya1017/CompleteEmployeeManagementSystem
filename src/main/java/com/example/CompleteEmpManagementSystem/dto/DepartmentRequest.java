package com.example.CompleteEmpManagementSystem.dto;

public class DepartmentRequest {

    // Name of the department
    private String name;

    // Description of the department
    private String description;

    public DepartmentRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}