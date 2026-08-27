package com.example.CompleteEmpManagementSystem.controller;

import com.example.CompleteEmpManagementSystem.dto.ChangePasswordRequest;
import com.example.CompleteEmpManagementSystem.dto.RoleUpdateRequest;
import com.example.CompleteEmpManagementSystem.dto.StatusUpdateRequest;
import com.example.CompleteEmpManagementSystem.dto.UserResponse;
import com.example.CompleteEmpManagementSystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }



    // GET /api/users
    //
    // Only ADMIN can view all users.


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }



    // GET /api/users/{id}
    //
    // Only ADMIN can view a specific user.


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.getUserById(id)
        );
    }



    // PUT /api/users/{id}/roles
    //
    // Only ADMIN can change user roles.

    @PutMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> updateRoles(
            @PathVariable Long id,
            @RequestBody RoleUpdateRequest request) {

        return ResponseEntity.ok(
                userService.updateRoles(id, request)
        );
    }



    // PUT /api/users/{id}/status
    //
    // Only ADMIN can enable/disable a user.


    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> updateStatus(
            @PathVariable Long id,
            @RequestBody StatusUpdateRequest request) {

        return ResponseEntity.ok(
                userService.updateStatus(id, request)
        );
    }

// PUT /api/users/change-password
//
// Any authenticated user can change their own password.


    @PutMapping("/change-password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> changePassword(
            @RequestBody ChangePasswordRequest request) {

        userService.changePassword(request);

        return ResponseEntity.ok(
                "Password changed successfully"
        );
    }
}