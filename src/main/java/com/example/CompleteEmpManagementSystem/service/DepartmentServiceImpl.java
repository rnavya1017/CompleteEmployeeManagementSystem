package com.example.CompleteEmpManagementSystem.service;

import com.example.CompleteEmpManagementSystem.dto.DepartmentRequest;
import com.example.CompleteEmpManagementSystem.dto.DepartmentResponse;
import com.example.CompleteEmpManagementSystem.dto.EmployeeResponse;
import com.example.CompleteEmpManagementSystem.exception.DepartmentNotFoundException;
import com.example.CompleteEmpManagementSystem.model.Department;
import com.example.CompleteEmpManagementSystem.model.Employee;
import com.example.CompleteEmpManagementSystem.repository.DepartmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(
            DepartmentRepository departmentRepository) {

        this.departmentRepository = departmentRepository;
    }

    // CREATE DEPARTMENT


    @Override
    public DepartmentResponse createDepartment(
            DepartmentRequest request) {

        // Check whether a department with this name
        // already exists
        if (departmentRepository.existsByName(request.getName())) {

            throw new RuntimeException(
                    "Department already exists with name: "
                            + request.getName()
            );
        }

        // Create a new Department Java object
        Department department = new Department();

        // Set department name
        department.setName(request.getName());

        // Set department description
        department.setDescription(request.getDescription());

        // Save department in PostgreSQL
        Department savedDepartment =
                departmentRepository.save(department);

        // Convert entity to response DTO
        return new DepartmentResponse(savedDepartment);
    }

    public List<Department> getdepartments(){
        return departmentRepository.findAll();
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {

        // Find employee by ID
        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id: " + id
                        )
                );

        // Convert Employee entity to EmployeeResponse
        return new DepartmentResponse(department);
    }


    public DepartmentResponse updateDepartment(
            Long id,
            DepartmentRequest departmentRequest) {

        // Find the existing department
        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id: " + id
                        )
                );

        // Update the existing department
        department.setName(departmentRequest.getName());
        department.setDescription(departmentRequest.getDescription());

        // Save the updated department
        Department savedDept = departmentRepository.save(department);

        // Convert entity to response
        return new DepartmentResponse(savedDept);
    }

    public void deleteDepartment(Long id){
        // Find the existing department
        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id: " + id
                        )
                );
        departmentRepository.deleteById(id);

    }
}