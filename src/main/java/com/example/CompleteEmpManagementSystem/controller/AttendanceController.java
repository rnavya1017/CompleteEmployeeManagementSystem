package com.example.CompleteEmpManagementSystem.controller;

import com.example.CompleteEmpManagementSystem.dto.AttendanceRequest;
import com.example.CompleteEmpManagementSystem.dto.AttendanceResponse;
import com.example.CompleteEmpManagementSystem.service.AttendanceService;
import com.example.CompleteEmpManagementSystem.security.EmployeeSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;


    public AttendanceController(
            AttendanceService attendanceService) {

        this.attendanceService = attendanceService;
    }



    // POST /api/attendance
    //
    // ADMIN and HR can create attendance.


    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<AttendanceResponse> createAttendance(
            @RequestBody AttendanceRequest request) {

        AttendanceResponse response =
                attendanceService.createAttendance(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // GET /api/attendance/employee/{id}
    //
    // ADMIN → any employee's attendance
    // HR → any employee's attendance
    // EMPLOYEE → only own attendance


    @GetMapping("/employee/{id}")
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'HR') or " +
                    "(hasRole('EMPLOYEE') and " +
                    "@employeeSecurity.isOwner(#id, authentication))"
    )
    public ResponseEntity<List<AttendanceResponse>>
    getAttendanceByEmployee(
            @PathVariable Long id) {

        List<AttendanceResponse> responses =
                attendanceService.getAttendanceByEmployee(id);

        return ResponseEntity.ok(responses);
    }


    // GET /api/attendance/date/{date}
    //
    // Only ADMIN and HR can view attendance by date.


    @GetMapping("/date/{date}")
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<List<AttendanceResponse>>
    getAttendanceByDate(
            @PathVariable LocalDate date) {

        List<AttendanceResponse> responses =
                attendanceService.getAttendanceByDate(date);

        return ResponseEntity.ok(responses);
    }
}