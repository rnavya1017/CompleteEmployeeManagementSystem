package com.example.CompleteEmpManagementSystem.service;

import com.example.CompleteEmpManagementSystem.dto.SalaryRequest;
import com.example.CompleteEmpManagementSystem.dto.SalaryResponse;
import com.example.CompleteEmpManagementSystem.exception.EmployeeNotFoundException;
import com.example.CompleteEmpManagementSystem.model.Employee;
import com.example.CompleteEmpManagementSystem.model.Salary;
import com.example.CompleteEmpManagementSystem.repository.EmployeeRepository;
import com.example.CompleteEmpManagementSystem.repository.SalaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;


    public SalaryServiceImpl(
            SalaryRepository salaryRepository,
            EmployeeRepository employeeRepository) {

        this.salaryRepository = salaryRepository;
        this.employeeRepository = employeeRepository;
    }


    // POST /api/salaries


    @Override
    public SalaryResponse createSalary(
            SalaryRequest request) {

        // Find the existing Employee
        Employee employee =
                employeeRepository.findById(
                        request.getEmployeeId()
                ).orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: "
                                        + request.getEmployeeId()
                        )
                );


        // Create Salary Java object
        Salary salary = new Salary();


        // Set salary information
        salary.setBasicSalary(request.getBasicSalary());
        salary.setHra(request.getHra());
        salary.setBonus(request.getBonus());
        salary.setDeductions(request.getDeductions());
        salary.setNetSalary(request.getNetSalary());

        salary.setSalaryMonth(request.getSalaryMonth());
        salary.setSalaryYear(request.getSalaryYear());


        // Associate existing Employee
        //
        // This does NOT insert another Employee.
        // It creates the relationship:
        //
        // Salary → Employee
        //
        salary.setEmployee(employee);


        // Save Salary
        Salary savedSalary =
                salaryRepository.save(salary);


        return new SalaryResponse(savedSalary);
    }


    // GET /api/salaries/employee/{id}


    @Override
    public List<SalaryResponse> getSalaryByEmployee(
            Long employeeId) {

        // Check Employee exists
        employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: "
                                        + employeeId
                        )
                );


        return salaryRepository
                .findByEmployeeId(employeeId)
                .stream()
                .map(SalaryResponse::new)
                .toList();
    }



    // GET /api/salaries/employee/{id}?month=8&year=2026


    @Override
    public List<SalaryResponse> getSalaryByEmployeeAndMonthYear(
            Long employeeId,
            Integer month,
            Integer year) {

        // Check Employee exists
        employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: "
                                        + employeeId
                        )
                );


        return salaryRepository
                .findByEmployeeIdAndSalaryMonthAndSalaryYear(
                        employeeId,
                        month,
                        year
                )
                .stream()
                .map(SalaryResponse::new)
                .toList();
    }
}