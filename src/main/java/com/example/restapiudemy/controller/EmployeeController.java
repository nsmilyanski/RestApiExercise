package com.example.restapiudemy.controller;

import com.example.restapiudemy.exceptions.ResourceNotFoundException;
import com.example.restapiudemy.model.Employee;
import com.example.restapiudemy.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    // build get all employees REST API
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) {
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.updateEmployee(id, employee), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {

        // delete employee from DB
        employeeService.deleteEmployee(id);

        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
    }
}
