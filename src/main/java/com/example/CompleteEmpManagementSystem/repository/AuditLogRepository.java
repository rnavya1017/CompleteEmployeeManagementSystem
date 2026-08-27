package com.example.CompleteEmpManagementSystem.repository;

import com.example.CompleteEmpManagementSystem.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository
        extends JpaRepository<AuditLog, Long> {

    // Find audit logs created by a particular username
    List<AuditLog> findByUsername(String username);
}