package com.example.CompleteEmpManagementSystem.exception;

/**
 * Thrown when a department cannot be found.
 */
public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(String message) {
        super(message);
    }
}