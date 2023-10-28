package com.example.backendproiect.controller;

import com.example.backendproiect.entities.Employee;
import com.example.backendproiect.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping()
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id)
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        return employeeService.getEmployeeById(id)
                .map(existingEmployee -> {
                    Employee updatedEmployee = employeeService.updateEmployee(id, employee);
                    return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employee/department/{departmentId}")
    public ResponseEntity<?>getAllEmployeesPerDepartment(@PathVariable Integer departmentId)
    {
        return ResponseEntity.ok(this.employeeService.getAllEmployeesByDepartment(departmentId));
    }
    @GetMapping("/manager/department/{departmentId}")
    public ResponseEntity<?>getAllManagersPerDepartment(@PathVariable Integer departmentId)
    {
        return ResponseEntity.ok(this.employeeService.getAllManagersByDepartment(departmentId));
    }
}

