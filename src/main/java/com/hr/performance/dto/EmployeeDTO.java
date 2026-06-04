package com.hr.performance.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDTO {

    private Long id;
    private String name;
    private String department;
    private String role;
    private LocalDate joiningDate;
    private Double averageRating;
}