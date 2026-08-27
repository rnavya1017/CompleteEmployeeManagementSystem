package com.example.CompleteEmpManagementSystem.dto;

import com.example.CompleteEmpManagementSystem.model.RoleName;

import java.util.Set;

public class RoleUpdateRequest {

    private Set<RoleName> roles;


    public RoleUpdateRequest() {
    }


    public Set<RoleName> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleName> roles) {
        this.roles = roles;
    }
}