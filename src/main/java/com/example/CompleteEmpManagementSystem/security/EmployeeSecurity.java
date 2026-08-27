package com.example.CompleteEmpManagementSystem.security;

import com.example.CompleteEmpManagementSystem.model.Employee;
import com.example.CompleteEmpManagementSystem.repository.EmployeeRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSecurity {

    private final EmployeeRepository employeeRepository;

    public EmployeeSecurity(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }



    public boolean isOwner(Long employeeId, Authentication authentication) {

        // Find the employee record
        Employee employee = employeeRepository.findById(employeeId)
                .orElse(null);

        // If employee does not exist, access is denied
        if (employee == null) {
            return false;
        }

        // Get the username of the currently authenticated user
        String loggedInUsername = authentication.getName();

        // Check whether the Employee is linked to a User
        if (employee.getUser() == null) {
            return false;
        }

        // Compare logged-in username with employee's user username
        return employee.getUser()
                .getUsername()
                .equals(loggedInUsername);
    }
}