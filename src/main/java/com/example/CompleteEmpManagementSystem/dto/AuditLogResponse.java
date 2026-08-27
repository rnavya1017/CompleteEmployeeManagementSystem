package com.example.CompleteEmpManagementSystem.dto;

import com.example.CompleteEmpManagementSystem.model.AuditLog;

import java.time.LocalDateTime;

public class AuditLogResponse {

    private Long id;

    private String username;

    private String action;

    private String methodName;

    private String requestUri;

    private LocalDateTime timestamp;

    private Long executionTime;

    private Integer status;


    public AuditLogResponse(AuditLog auditLog) {

        this.id = auditLog.getId();
        this.username = auditLog.getUsername();
        this.action = auditLog.getAction();
        this.methodName = auditLog.getMethodName();
        this.requestUri = auditLog.getRequestUri();
        this.timestamp = auditLog.getTimestamp();
        this.executionTime = auditLog.getExecutionTime();
        this.status = auditLog.getStatus();
    }


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAction() {
        return action;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public Integer getStatus() {
        return status;
    }
}