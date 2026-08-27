package com.example.CompleteEmpManagementSystem.controller;

import com.example.CompleteEmpManagementSystem.dto.SalaryRequest;
import com.example.CompleteEmpManagementSystem.dto.SalaryResponse;
import com.example.CompleteEmpManagementSystem.service.SalaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {

    private final SalaryService salaryService;


    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }


    // POST /api/salaries
    //
    // ADMIN and HR can create salary records.


    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<SalaryResponse> createSalary(
            @RequestBody SalaryRequest request) {

        SalaryResponse response =
                salaryService.createSalary(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // GET /api/salaries/employee/{id}


    @GetMapping("/employee/{id}")
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'HR') or " +
                    "(hasRole('EMPLOYEE') and " +
                    "@employeeSecurity.isOwner(#id, authentication))"
    )
    public ResponseEntity<List<SalaryResponse>> getSalaryByEmployee(
            @PathVariable Long id) {

        List<SalaryResponse> responses =
                salaryService.getSalaryByEmployee(id);

        return ResponseEntity.ok(responses);
    }



    // GET /api/salaries/employee/{id}?month=&year=



    @GetMapping(
            value = "/employee/{id}",
            params = {"month", "year"}
    )
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'HR') or " +
                    "(hasRole('EMPLOYEE') and " +
                    "@employeeSecurity.isOwner(#id, authentication))"
    )
    public ResponseEntity<List<SalaryResponse>>
    getSalaryByEmployeeAndMonthYear(
            @PathVariable Long id,
            @RequestParam Integer month,
            @RequestParam Integer year) {

        List<SalaryResponse> responses =
                salaryService.getSalaryByEmployeeAndMonthYear(
                        id,
                        month,
                        year
                );

        return ResponseEntity.ok(responses);
    }
}