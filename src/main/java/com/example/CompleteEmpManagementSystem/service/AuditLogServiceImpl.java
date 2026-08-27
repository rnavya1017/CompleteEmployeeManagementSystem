package com.example.CompleteEmpManagementSystem.service;

import com.example.CompleteEmpManagementSystem.dto.AuditLogResponse;
import com.example.CompleteEmpManagementSystem.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogServiceImpl
        implements AuditLogService {

    private final AuditLogRepository auditLogRepository;


    public AuditLogServiceImpl(
            AuditLogRepository auditLogRepository) {

        this.auditLogRepository = auditLogRepository;
    }


    // GET ALL AUDIT LOGS


    @Override
    public List<AuditLogResponse> getAllLogs() {

        return auditLogRepository.findAll()
                .stream()
                .map(AuditLogResponse::new)
                .toList();
    }



    // GET AUDIT LOGS BY USERNAME


    @Override
    public List<AuditLogResponse> getLogsByUsername(
            String username) {

        return auditLogRepository
                .findByUsername(username)
                .stream()
                .map(AuditLogResponse::new)
                .toList();
    }
}