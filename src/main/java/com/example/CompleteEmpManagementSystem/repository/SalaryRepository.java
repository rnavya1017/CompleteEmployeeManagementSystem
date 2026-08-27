package com.example.CompleteEmpManagementSystem.repository;

import com.example.CompleteEmpManagementSystem.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryRepository
        extends JpaRepository<Salary, Long> {

    // Get all salary records of an employee
    List<Salary> findByEmployeeId(Long employeeId);


    // Get salary records of an employee
    // for a particular month and year
    List<Salary> findByEmployeeIdAndSalaryMonthAndSalaryYear(
            Long employeeId,
            Integer salaryMonth,
            Integer salaryYear
    );
}