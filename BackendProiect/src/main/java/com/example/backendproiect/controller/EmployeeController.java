package com.example.backendproiect.controller;

import com.example.backendproiect.dto.ResetPasswordReq;
import com.example.backendproiect.entities.Employee;
import com.example.backendproiect.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping()
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
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
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        try {
            return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
        }catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Cannot create user with same email.");
        }
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

    @PostMapping("/employee/rpassword")
    public ResponseEntity<?>resetEmployeePassword(@RequestBody ResetPasswordReq resetPasswordReq) throws Exception {
        System.out.println(resetPasswordReq.toString());
        this.employeeService.resetPassword(resetPasswordReq);
        return ResponseEntity.ok("Successfully changed password");
    }
}

