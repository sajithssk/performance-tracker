package com.hr.performance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopPerformerDTO {

    private Long employeeId;
    private String employeeName;
    private Double averageRating;
}