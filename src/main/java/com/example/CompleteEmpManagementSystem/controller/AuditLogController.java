package com.example.CompleteEmpManagementSystem.controller;

import com.example.CompleteEmpManagementSystem.dto.AuditLogResponse;
import com.example.CompleteEmpManagementSystem.service.AuditLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {

    private final AuditLogService auditLogService;


    public AuditLogController(
            AuditLogService auditLogService) {

        this.auditLogService = auditLogService;
    }


    // ==========================================================
    // GET /api/audit-logs
    //
    // ADMIN only
    // ==========================================================

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AuditLogResponse>> getAllLogs() {

        return ResponseEntity.ok(
                auditLogService.getAllLogs()
        );
    }


    // ==========================================================
    // GET /api/audit-logs/user/{username}
    //
    // ADMIN only
    // ==========================================================

    @GetMapping("/user/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AuditLogResponse>>
    getLogsByUsername(
            @PathVariable String username) {

        return ResponseEntity.ok(
                auditLogService
                        .getLogsByUsername(username)
        );
    }
}