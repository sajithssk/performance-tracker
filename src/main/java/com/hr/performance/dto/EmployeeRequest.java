package com.hr.performance.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String department;

    @NotBlank
    private String role;

    @NotBlank
    private LocalDate joiningDate;
}
