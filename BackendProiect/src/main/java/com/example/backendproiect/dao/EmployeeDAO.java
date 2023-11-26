package com.example.backendproiect.dao;

import com.example.backendproiect.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee,Integer> {
    @Query("SELECT u FROM Employee u WHERE u.role = 'Employee' AND u.departmentId=?1")
    List<Employee>getEmployeesByDepartmentIdAndRole(Integer departmentId);
    @Query("SELECT u FROM Employee u WHERE u.role = 'Manager' AND u.departmentId=?1")
    List<Employee>getManagersByDepartmentIdAndRole(Integer departmentId);
    Employee getEmployeeByEmail(String email);
}
