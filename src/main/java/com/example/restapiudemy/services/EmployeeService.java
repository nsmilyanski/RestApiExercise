package com.example.restapiudemy.services;

import com.example.restapiudemy.model.Employee;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(long employeeId);

    Employee updateEmployee(long id, Employee employee);

    void deleteEmployee(long id);
}
