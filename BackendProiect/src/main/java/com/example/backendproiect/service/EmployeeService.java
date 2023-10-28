package com.example.backendproiect.service;

import com.example.backendproiect.dao.EmployeeDAO;
import com.example.backendproiect.dao.UserDAO;
import com.example.backendproiect.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDAO employeeRepository;
    @Autowired
    private UserDAO userRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer id, Employee employee) {
        if (employeeRepository.existsById(id)) {
            employee.setId(id);
            return employeeRepository.save(employee);
        } else {
            throw new UsernameNotFoundException("Employee not found with id: " + id);
        }
    }

    public void deleteEmployee(Integer id) {
        if (employeeRepository.existsById(id)) {
            try {
                String userEmail=employeeRepository.findById(id).get().getEmail();
                employeeRepository.deleteById(id);
                userRepository.deleteById(userEmail);
            }catch (Exception e)
            {throw new UsernameNotFoundException("User was not found");}
        } else {
            throw new UsernameNotFoundException("Employee not found with id: " + id);
        }
    }
    public List<Employee> getAllEmployeesByDepartment(Integer departmentId)
    {
        return this.employeeRepository.getEmployeesByDepartmentIdAndRole(departmentId);
    }
    public List<Employee> getAllManagersByDepartment(Integer departmentId)
    {
        return this.employeeRepository.getManagersByDepartmentIdAndRole(departmentId);
    }
}
