package com.example.backendproiect.dao;

import com.example.backendproiect.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDAO extends JpaRepository<Department,Integer> {
}
