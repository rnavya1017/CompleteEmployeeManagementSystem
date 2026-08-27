package com.example.CompleteEmpManagementSystem.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceRequest {

    // ID of the Employee for whom attendance is being created
    private Long employeeId;

    // Attendance date
    private LocalDate date;

    // Employee check-in time
    private LocalTime checkIn;

    // Employee check-out time
    private LocalTime checkOut;

    // Attendance status
    // Examples: PRESENT, ABSENT, LEAVE
    private String status;


    public AttendanceRequest() {
    }


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public LocalTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalTime checkIn) {
        this.checkIn = checkIn;
    }


    public LocalTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalTime checkOut) {
        this.checkOut = checkOut;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}