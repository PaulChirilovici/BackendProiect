package com.example.backendproiect.controller;

import com.example.backendproiect.entities.Department;
import com.example.backendproiect.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getDepartments());
    }

    @PostMapping()
    public Department saveDepartment(
            @RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    // Update operation
    @PutMapping("/{id}")
    public String updateDepartment(@RequestBody String newDescription, @PathVariable("id") Integer departmentId) {
        try {
            departmentService.updateDepartment(departmentId, newDescription);
            return "Updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to update...";
        }
    }

    // Delete operation
    @DeleteMapping("/{id}")
    public String deleteDepartmentById(@PathVariable("id") Integer departmentId) {
        departmentService.deleteDepartmentById(departmentId);

        return "Deleted Successfully";
    }

}
