package com.example.CompleteEmpManagementSystem.repository;

import com.example.CompleteEmpManagementSystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Checks whether a department with the given name already exists
    boolean existsByName(String name);
}