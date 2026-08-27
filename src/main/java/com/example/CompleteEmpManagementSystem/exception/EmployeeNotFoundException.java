package com.example.CompleteEmpManagementSystem.exception;

/**
 * Thrown when an employee cannot be found
 * using the provided employee ID.
 */
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}