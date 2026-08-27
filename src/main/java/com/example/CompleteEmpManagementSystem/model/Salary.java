package com.example.CompleteEmpManagementSystem.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "salary")
public class Salary {


    // Primary Key


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    // Employee 1 -------- * Salary


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;



    // Salary details


    @Column(name = "basic_salary", nullable = false)
    private BigDecimal basicSalary;


    @Column(nullable = false)
    private BigDecimal hra;


    @Column(nullable = false)
    private BigDecimal bonus;


    @Column(nullable = false)
    private BigDecimal deductions;


    @Column(name = "net_salary", nullable = false)
    private BigDecimal netSalary;


    // Salary month
    // Example: 8 for August
    @Column(name = "salary_month", nullable = false)
    private Integer salaryMonth;


    // Salary year
    // Example: 2026
    @Column(name = "salary_year", nullable = false)
    private Integer salaryYear;


    // Constructors


    public Salary() {
    }



    // Getters and Setters


    public Long getId() {
        return id;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }


    public BigDecimal getHra() {
        return hra;
    }

    public void setHra(BigDecimal hra) {
        this.hra = hra;
    }


    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }


    public BigDecimal getDeductions() {
        return deductions;
    }

    public void setDeductions(BigDecimal deductions) {
        this.deductions = deductions;
    }


    public BigDecimal getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(BigDecimal netSalary) {
        this.netSalary = netSalary;
    }


    public Integer getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(Integer salaryMonth) {
        this.salaryMonth = salaryMonth;
    }


    public Integer getSalaryYear() {
        return salaryYear;
    }

    public void setSalaryYear(Integer salaryYear) {
        this.salaryYear = salaryYear;
    }
}