package com.hr.performance.controller;

import com.hr.performance.dto.EmployeeDTO;
import com.hr.performance.dto.EmployeeRequest;
import com.hr.performance.dto.EmployeeReviewDTO;
import com.hr.performance.entity.Employee;
import com.hr.performance.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(request));
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List <EmployeeReviewDTO>> getEmployeeReviews(@PathVariable long id) {
        return  ResponseEntity.ok(employeeService.getEmployeeReviews(id));
    }

    @GetMapping
    public ResponseEntity<List <EmployeeDTO>> filterEmployees(@RequestParam(required = false) String department,
                                                              @RequestParam(required = false) Double minRating){
        return ResponseEntity.ok(employeeService.filterEmployees(department, minRating));
    }
}
