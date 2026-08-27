package com.example.CompleteEmpManagementSystem.service;

import com.example.CompleteEmpManagementSystem.dto.AttendanceRequest;
import com.example.CompleteEmpManagementSystem.dto.AttendanceResponse;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    // Create attendance for an employee
    AttendanceResponse createAttendance(
            AttendanceRequest request
    );

    // Get attendance of one employee
    List<AttendanceResponse> getAttendanceByEmployee(
            Long employeeId
    );

    // Get attendance records for a particular date
    List<AttendanceResponse> getAttendanceByDate(
            LocalDate date
    );
}