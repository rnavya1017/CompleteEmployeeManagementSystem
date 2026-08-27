package com.example.CompleteEmpManagementSystem.service;

import com.example.CompleteEmpManagementSystem.dto.AuditLogResponse;

import java.util.List;

public interface AuditLogService {

    // Get all audit logs
    List<AuditLogResponse> getAllLogs();

    // Get audit logs for a particular user
    List<AuditLogResponse> getLogsByUsername(
            String username
    );
}