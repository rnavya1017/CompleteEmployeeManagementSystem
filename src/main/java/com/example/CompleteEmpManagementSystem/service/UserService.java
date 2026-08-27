package com.example.CompleteEmpManagementSystem.service;

import com.example.CompleteEmpManagementSystem.dto.ChangePasswordRequest;
import com.example.CompleteEmpManagementSystem.dto.RoleUpdateRequest;
import com.example.CompleteEmpManagementSystem.dto.StatusUpdateRequest;
import com.example.CompleteEmpManagementSystem.dto.UserResponse;

import java.util.List;

public interface UserService {

    // Get all users
    List<UserResponse> getAllUsers();

    // Get one user
    UserResponse getUserById(Long id);

    // Update user's roles
    UserResponse updateRoles(
            Long id,
            RoleUpdateRequest request
    );

    // Enable or disable user
    UserResponse updateStatus(
            Long id,
            StatusUpdateRequest request
    );


    // Change password of logged-in user
    void changePassword(ChangePasswordRequest request);
}