package com.example.restapiudemy.services;

import com.example.restapiudemy.exceptions.ResourceNotFoundException;
import com.example.restapiudemy.model.Employee;
import com.example.restapiudemy.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId));
    }

    @Override
    public Employee updateEmployee(long id, Employee employee) {

        Optional<Employee> worker = employeeRepository.findById(id);

        if (worker.isPresent()) {
            worker.get().setFirstName(employee.getFirstName());
            worker.get().setLastName(employee.getLastName());
            worker.get().setEmail(employee.getEmail());
            employeeRepository.save(worker.get());
        }else {
            throw new ResourceNotFoundException("Employee", "id", id);
        }

        return worker.get();
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

        employeeRepository.deleteById(id);
    }
}
