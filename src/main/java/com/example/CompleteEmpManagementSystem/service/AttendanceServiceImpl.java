package com.example.CompleteEmpManagementSystem.service;

import com.example.CompleteEmpManagementSystem.dto.AttendanceRequest;
import com.example.CompleteEmpManagementSystem.dto.AttendanceResponse;
import com.example.CompleteEmpManagementSystem.exception.EmployeeNotFoundException;
import com.example.CompleteEmpManagementSystem.model.Attendance;
import com.example.CompleteEmpManagementSystem.model.Employee;
import com.example.CompleteEmpManagementSystem.repository.AttendanceRepository;
import com.example.CompleteEmpManagementSystem.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;


    public AttendanceServiceImpl(
            AttendanceRepository attendanceRepository,
            EmployeeRepository employeeRepository) {

        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }



    // POST /api/attendance


    @Override
    public AttendanceResponse createAttendance(
            AttendanceRequest request) {

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


        // Create a new Attendance Java object
        Attendance attendance = new Attendance();


        // Set attendance information
        attendance.setDate(request.getDate());
        attendance.setCheckIn(request.getCheckIn());
        attendance.setCheckOut(request.getCheckOut());
        attendance.setStatus(request.getStatus());


        // Associate the existing Employee
        //
        // This creates:
        //
        // Attendance → Employee
        //
        attendance.setEmployee(employee);


        // Save attendance in PostgreSQL
        Attendance savedAttendance =
                attendanceRepository.save(attendance);


        // Convert entity to response DTO
        return new AttendanceResponse(savedAttendance);
    }



    // GET /api/attendance/employee/{id}


    @Override
    public List<AttendanceResponse> getAttendanceByEmployee(
            Long employeeId) {

        // Check whether Employee exists
        employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: "
                                        + employeeId
                        )
                );


        // Find all attendance records for this employee
        return attendanceRepository
                .findByEmployeeId(employeeId)
                .stream()
                .map(AttendanceResponse::new)
                .toList();
    }



    // GET /api/attendance/date/{date}


    @Override
    public List<AttendanceResponse> getAttendanceByDate(
            LocalDate date) {

        return attendanceRepository
                .findByDate(date)
                .stream()
                .map(AttendanceResponse::new)
                .toList();
    }
}