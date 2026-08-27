package com.example.CompleteEmpManagementSystem.repository;

import com.example.CompleteEmpManagementSystem.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {

    // Find all attendance records for a particular employee
    List<Attendance> findByEmployeeId(Long employeeId);

    // Find all attendance records for a particular date
    List<Attendance> findByDate(LocalDate date);
}