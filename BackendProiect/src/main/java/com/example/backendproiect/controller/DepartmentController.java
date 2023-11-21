package com.example.backendproiect.controller;

import com.example.backendproiect.dto.DepartmentDto;
import com.example.backendproiect.entities.Department;
import com.example.backendproiect.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@EnableWebSecurity
@RequestMapping("/departments")

public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getDepartments());
    }

    @PostMapping()
    public ResponseEntity<?> saveDepartment(
            @RequestBody Department department) {
        try {
            return ResponseEntity.ok(departmentService.saveDepartment(department));
        }catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Cannot save 2 departments with the same description.");
        }
    }

    // Update operation
    @PutMapping("/{id}")
    public String updateDepartment(@RequestBody DepartmentDto departmentDto, @PathVariable("id") Integer departmentId) {
        try {
            departmentService.updateDepartment(departmentId, departmentDto.getDescription());
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
