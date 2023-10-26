package com.example.backendproiect.service;

import com.example.backendproiect.dao.DepartmentDAO;
import com.example.backendproiect.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDAO departmentRepository;

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public void deleteDepartmentById(Integer id) {
        departmentRepository.deleteById(id);
    }

    public void updateDepartment(Integer id, String newDescription) {
        Optional<Department> departmentFromDB = this.departmentRepository.findById(id);
        if (departmentFromDB.isPresent()) {
            departmentFromDB.get().setDescription(newDescription);
            departmentRepository.save(departmentFromDB.get());
        }

    }

}
