package com.example.CompleteEmpManagementSystem.dto;

import com.example.CompleteEmpManagementSystem.model.RoleName;
import com.example.CompleteEmpManagementSystem.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private boolean enabled;

    private Set<RoleName> roles;


    public UserResponse(User user) {

        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.enabled = user.isEnabled();

        // Convert Role objects into RoleName values
        this.roles = user.getRoles()
                .stream()
                .map(role -> role.getName())
                .collect(Collectors.toSet());
    }


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<RoleName> getRoles() {
        return roles;
    }
}