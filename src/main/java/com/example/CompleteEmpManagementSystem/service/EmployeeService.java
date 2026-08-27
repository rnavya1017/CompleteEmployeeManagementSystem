package com.example.CompleteEmpManagementSystem.service;

import com.example.CompleteEmpManagementSystem.dto.EmployeeRequest;
import com.example.CompleteEmpManagementSystem.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    // Create a new employee
    EmployeeResponse createEmployee(EmployeeRequest request);

    // Get all employees
    List<EmployeeResponse> getAllEmployees();

    // Update an existing employee
    EmployeeResponse updateEmployee(
            Long id,
            EmployeeRequest request
    );

    // Delete an employee
    void deleteEmployee(Long id);

    // Search employees by name
    List<EmployeeResponse> searchEmployeesByName(String name);


    // Get employee by ID
    EmployeeResponse getEmployeeById(Long id);
}