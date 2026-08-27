package com.example.CompleteEmpManagementSystem.dto;

import com.example.CompleteEmpManagementSystem.model.Employee;

import java.time.LocalDate;

public class EmployeeResponse {

    private Long id;
    private String employeeCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfJoining;
    private String designation;
    private Double salary;

    private Long userId;
    private Long departmentId;


    public EmployeeResponse(Employee employee) {

        this.id = employee.getId();
        this.employeeCode = employee.getEmployeeCode();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.phone = employee.getPhone();
        this.dateOfJoining = employee.getDateOfJoining();
        this.designation = employee.getDesignation();
        this.salary = employee.getSalary();

        // Get the associated User ID
        if (employee.getUser() != null) {
            this.userId = employee.getUser().getId();
        }

        // Get the associated Department ID
        if (employee.getDepartment() != null) {
            this.departmentId = employee.getDepartment().getId();
        }
    }


    public Long getId() {
        return id;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public String getDesignation() {
        return designation;
    }

    public Double getSalary() {
        return salary;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }
}