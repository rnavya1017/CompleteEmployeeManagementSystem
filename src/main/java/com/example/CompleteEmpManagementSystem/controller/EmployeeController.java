package com.example.CompleteEmpManagementSystem.controller;

import com.example.CompleteEmpManagementSystem.dto.EmployeeRequest;
import com.example.CompleteEmpManagementSystem.dto.EmployeeResponse;
import com.example.CompleteEmpManagementSystem.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class   EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    // GET /api/employees
    //
    // ADMIN and HR can view all employees.

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {

        List<EmployeeResponse> employees =
                employeeService.getAllEmployees();

        return ResponseEntity.ok(employees);
    }


    // POST /api/employees
    //
    // ADMIN and HR can create employees.


    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<EmployeeResponse> createEmployee(
            @RequestBody EmployeeRequest request) {

        EmployeeResponse response =
                employeeService.createEmployee(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // GET /api/employees/search?name={name}
    //
    // ADMIN and HR can search employees.
    //
    // IMPORTANT:
    // Keep this mapping before /{id}.

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<List<EmployeeResponse>> searchEmployees(
            @RequestParam String name) {

        List<EmployeeResponse> employees =
                employeeService.searchEmployeesByName(name);

        return ResponseEntity.ok(employees);
    }


    // PUT /api/employees/{id}
    //
    // ADMIN and HR can update employees.

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeRequest request) {

        EmployeeResponse response =
                employeeService.updateEmployee(id, request);

        return ResponseEntity.ok(response);
    }



    // DELETE /api/employees/{id}
    //
    // Only ADMIN can delete employees.


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'HR') or " +
                    "(hasRole('EMPLOYEE') and " +
                    "@employeeSecurity.isOwner(#id, authentication))"
    )
    public ResponseEntity<EmployeeResponse> getEmployeeById(
            @PathVariable Long id) {

        EmployeeResponse response =
                employeeService.getEmployeeById(id);

        return ResponseEntity.ok(response);
    }


}