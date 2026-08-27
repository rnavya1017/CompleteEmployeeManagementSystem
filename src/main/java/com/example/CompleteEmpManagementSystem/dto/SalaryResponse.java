package com.example.CompleteEmpManagementSystem.dto;

import com.example.CompleteEmpManagementSystem.model.Salary;

import java.math.BigDecimal;

public class SalaryResponse {

    private Long id;
    private Long employeeId;

    private BigDecimal basicSalary;
    private BigDecimal hra;
    private BigDecimal bonus;
    private BigDecimal deductions;
    private BigDecimal netSalary;

    private Integer salaryMonth;
    private Integer salaryYear;


    // IMPORTANT:
    // This constructor accepts a Salary object.
    // It is required for SalaryResponse::new.
    public SalaryResponse(Salary salary) {

        this.id = salary.getId();

        // We only return employee ID,
        // not the complete Employee object.
        if (salary.getEmployee() != null) {
            this.employeeId = salary.getEmployee().getId();
        }

        this.basicSalary = salary.getBasicSalary();
        this.hra = salary.getHra();
        this.bonus = salary.getBonus();
        this.deductions = salary.getDeductions();
        this.netSalary = salary.getNetSalary();

        this.salaryMonth = salary.getSalaryMonth();
        this.salaryYear = salary.getSalaryYear();
    }


    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public BigDecimal getHra() {
        return hra;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public BigDecimal getDeductions() {
        return deductions;
    }

    public BigDecimal getNetSalary() {
        return netSalary;
    }

    public Integer getSalaryMonth() {
        return salaryMonth;
    }

    public Integer getSalaryYear() {
        return salaryYear;
    }
}