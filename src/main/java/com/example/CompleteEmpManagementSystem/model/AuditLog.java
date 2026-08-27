package com.example.CompleteEmpManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // Username of the user who performed the operation
    private String username;


    // Description of the action
    private String action;


    // HTTP method
    // Example: GET, POST, PUT, DELETE
    @Column(name = "method_name")
    private String methodName;


    // Requested API path
    @Column(name = "request_uri")
    private String requestUri;


    // Time at which request was processed
    private LocalDateTime timestamp;


    // Execution time in milliseconds
    @Column(name = "execution_time")
    private Long executionTime;


    // HTTP response status
    private Integer status;


    public AuditLog() {
    }


    public Long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}