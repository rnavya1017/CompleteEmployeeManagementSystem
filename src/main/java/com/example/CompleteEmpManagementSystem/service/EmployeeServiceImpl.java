package com.example.CompleteEmpManagementSystem.service;

import com.example.CompleteEmpManagementSystem.dto.EmployeeRequest;
import com.example.CompleteEmpManagementSystem.dto.EmployeeResponse;
import com.example.CompleteEmpManagementSystem.exception.DepartmentNotFoundException;
import com.example.CompleteEmpManagementSystem.exception.EmployeeNotFoundException;
import com.example.CompleteEmpManagementSystem.exception.UserNotFoundException;
import com.example.CompleteEmpManagementSystem.model.Department;
import com.example.CompleteEmpManagementSystem.model.Employee;
import com.example.CompleteEmpManagementSystem.model.User;
import com.example.CompleteEmpManagementSystem.repository.DepartmentRepository;
import com.example.CompleteEmpManagementSystem.repository.EmployeeRepository;
import com.example.CompleteEmpManagementSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;


    public EmployeeServiceImpl(
            EmployeeRepository employeeRepository,
            UserRepository userRepository,
            DepartmentRepository departmentRepository) {

        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }


    // CREATE EMPLOYEE
    // POST /api/employees

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {

        // Find the existing User
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: "
                                        + request.getUserId()
                        ));


        // Find the existing Department
        Department department =
                departmentRepository.findById(
                        request.getDepartmentId()
                ).orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id: "
                                        + request.getDepartmentId()
                        ));


        // Create a new Employee Java object
        Employee employee = new Employee();

        // Set employee information
        employee.setEmployeeCode(request.getEmployeeCode());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setDateOfJoining(request.getDateOfJoining());
        employee.setDesignation(request.getDesignation());
        employee.setSalary(request.getSalary());

        // Associate existing User
        employee.setUser(user);

        // Associate existing Department
        employee.setDepartment(department);


        // Save Employee to database
        Employee savedEmployee =
                employeeRepository.save(employee);


        return new EmployeeResponse(savedEmployee);
    }



    // GET ALL EMPLOYEES
    // GET /api/employees


    @Override
    public List<EmployeeResponse> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(EmployeeResponse::new)
                .toList();
    }


    // UPDATE EMPLOYEE
    // PUT /api/employees/{id}


    @Override
    public EmployeeResponse updateEmployee(
            Long id,
            EmployeeRequest request) {

        // Find existing employee
        Employee employee =
                employeeRepository.findById(id)
                        .orElseThrow(() ->
                                new EmployeeNotFoundException(
                                        "Employee not found with id: "
                                                + id
                                ));


        // Find the new/existing User
        User user =
                userRepository.findById(request.getUserId())
                        .orElseThrow(() ->
                                new UserNotFoundException(
                                        "User not found with id: "
                                                + request.getUserId()
                                ));


        // Find the Department
        Department department =
                departmentRepository.findById(
                        request.getDepartmentId()
                ).orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id: "
                                        + request.getDepartmentId()
                        ));


        // Update employee fields
        employee.setEmployeeCode(request.getEmployeeCode());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setDateOfJoining(request.getDateOfJoining());
        employee.setDesignation(request.getDesignation());
        employee.setSalary(request.getSalary());

        // Update User relationship
        employee.setUser(user);

        // Update Department relationship
        employee.setDepartment(department);


        // Save updated Employee
        Employee updatedEmployee =
                employeeRepository.save(employee);


        return new EmployeeResponse(updatedEmployee);
    }


    // DELETE EMPLOYEE
    // DELETE /api/employees/{id}


    @Override
    public void deleteEmployee(Long id) {

        // Check whether employee exists
        if (!employeeRepository.existsById(id)) {

            throw new EmployeeNotFoundException(
                    "Employee not found with id: " + id
            );
        }

        // Delete only the Employee
        //
        // The associated User and Department are NOT deleted.
        employeeRepository.deleteById(id);
    }


    // GET /api/employees/search?name={name}


    @Override
    public List<EmployeeResponse> searchEmployeesByName(
            String name) {

        return employeeRepository
                .findByFirstNameContainingIgnoreCase(name)
                .stream()
                .map(EmployeeResponse::new)
                .toList();
    }


// GET /api/employees/{id}


    @Override
    public EmployeeResponse getEmployeeById(Long id) {

        // Find employee by ID
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: " + id
                        )
                );

        // Convert Employee entity to EmployeeResponse
        return new EmployeeResponse(employee);
    }
}