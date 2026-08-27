package com.example.CompleteEmpManagementSystem.exception;

/**
 * Thrown when the supplied password is invalid.
 */
public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String message) {
        super(message);
    }
}