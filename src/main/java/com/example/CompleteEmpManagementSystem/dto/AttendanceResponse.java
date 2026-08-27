package com.example.CompleteEmpManagementSystem.dto;

import com.example.CompleteEmpManagementSystem.model.Attendance;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceResponse {

    private Long id;

    private Long employeeId;

    private LocalDate date;

    private LocalTime checkIn;

    private LocalTime checkOut;

    private String status;


    public AttendanceResponse(Attendance attendance) {

        this.id = attendance.getId();

        // Get employee ID instead of returning complete Employee object
        if (attendance.getEmployee() != null) {
            this.employeeId = attendance.getEmployee().getId();
        }

        this.date = attendance.getDate();
        this.checkIn = attendance.getCheckIn();
        this.checkOut = attendance.getCheckOut();
        this.status = attendance.getStatus();
    }


    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getCheckIn() {
        return checkIn;
    }

    public LocalTime getCheckOut() {
        return checkOut;
    }

    public String getStatus() {
        return status;
    }
}