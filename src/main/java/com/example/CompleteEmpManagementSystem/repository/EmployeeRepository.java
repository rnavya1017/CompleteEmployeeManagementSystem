package com.example.CompleteEmpManagementSystem.repository;

import com.example.CompleteEmpManagementSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Search employees by first name.
    // IgnoreCase makes "swathi" and "Swathi" equivalent.
    // Containing allows partial matching.
    List<Employee> findByFirstNameContainingIgnoreCase(String name);
}