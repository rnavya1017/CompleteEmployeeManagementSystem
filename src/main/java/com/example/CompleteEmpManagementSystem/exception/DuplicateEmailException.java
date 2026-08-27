package com.example.CompleteEmpManagementSystem.exception;

/**
 * Thrown when an email already exists in the system.
 */
public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String message) {
        super(message);
    }
}