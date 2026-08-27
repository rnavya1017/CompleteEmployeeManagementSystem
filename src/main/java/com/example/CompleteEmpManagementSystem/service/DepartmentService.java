package com.example.CompleteEmpManagementSystem.service;

import com.example.CompleteEmpManagementSystem.dto.DepartmentRequest;
import com.example.CompleteEmpManagementSystem.dto.DepartmentResponse;
import com.example.CompleteEmpManagementSystem.model.Department;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmentService {

    // Creates a new department
    DepartmentResponse createDepartment(DepartmentRequest request);

    List<Department> getdepartments();

    DepartmentResponse getDepartmentById(Long id);

    DepartmentResponse updateDepartment(Long id,DepartmentRequest departmentRequest);

    public void deleteDepartment(Long id);
}