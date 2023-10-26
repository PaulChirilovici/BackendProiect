package com.example.backendproiect.dao;

import com.example.backendproiect.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee,Integer> {
}
