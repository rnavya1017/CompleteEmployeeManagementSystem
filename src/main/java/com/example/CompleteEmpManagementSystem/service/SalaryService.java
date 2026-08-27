package com.example.CompleteEmpManagementSystem.service;

import com.example.CompleteEmpManagementSystem.dto.SalaryRequest;
import com.example.CompleteEmpManagementSystem.dto.SalaryResponse;

import java.util.List;

public interface SalaryService {

    // Create salary for an existing employee
    SalaryResponse createSalary(SalaryRequest request);


    // Get all salary records of an employee
    List<SalaryResponse> getSalaryByEmployee(Long employeeId);


    // Get salary of an employee for a particular month/year
    List<SalaryResponse> getSalaryByEmployeeAndMonthYear(
            Long employeeId,
            Integer month,
            Integer year
    );
}