package com.example.CompleteEmpManagementSystem.controller;

import com.example.CompleteEmpManagementSystem.dto.AuthResponse;
import com.example.CompleteEmpManagementSystem.dto.LoginRequest;
import com.example.CompleteEmpManagementSystem.dto.RegisterRequest;
import com.example.CompleteEmpManagementSystem.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request) {

        authService.register(request);

        return ResponseEntity.ok(
                "User registered successfully"
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}