package com.hr.performance.service;

import com.hr.performance.dto.*;
import com.hr.performance.entity.Employee;
import com.hr.performance.repository.EmployeeRepository;
import com.hr.performance.repository.PerformanceReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PerformanceReviewRepository reviewRepository;

    public Employee createEmployee(EmployeeRequest request) {
        Employee emp = new Employee();
        emp.setName(request.getName());
        emp.setDepartment(request.getDepartment());
        emp.setRole(request.getRole());
        emp.setJoiningDate(request.getJoiningDate());
        return employeeRepository.save(emp);
    }

    public List<EmployeeReviewDTO> getEmployeeReviews(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new EntityNotFoundException("Employee not found");
        }
        return reviewRepository.findReviewsWithCycleByEmployeeId(employeeId);
    }

    public List<EmployeeDTO> filterEmployees(String department, Double minRating) {
        List<Employee> employees;
        if (department != null && !department.isBlank()) {
            employees = employeeRepository.findByDepartment(department);
        } else {
            employees = employeeRepository.findAll();
        }
        List<Object[]> avgRows = reviewRepository.calculateAllAverageRatings();
        Map<Long, Double> avgMap = new HashMap<>();
        for (Object[] row : avgRows) {
            Long empId = ((Number) row[0]).longValue();
            Double avg = ((Number) row[1]).doubleValue();
            avgMap.put(empId, avg);
        }
        return employees.stream()
                .map(e -> {
                    EmployeeDTO dto = new EmployeeDTO();
                    dto.setId(e.getId());
                    dto.setName(e.getName());
                    dto.setDepartment(e.getDepartment());
                    dto.setRole(e.getRole());
                    dto.setJoiningDate(e.getJoiningDate());
                    dto.setAverageRating(avgMap.getOrDefault(e.getId(), 0.0));
                    return dto;
                })
                .filter(dto -> minRating == null || dto.getAverageRating() >= minRating)
                .collect(Collectors.toList());
    }
}