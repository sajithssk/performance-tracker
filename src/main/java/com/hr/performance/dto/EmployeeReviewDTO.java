package com.hr.performance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EmployeeReviewDTO {

    private Long reviewId;
    private Integer rating;
    private String reviewerNotes;
    private LocalDateTime submittedAt;
    private Long cycleId;
    private String cycleName;
    private LocalDate cycleStartDate;
    private LocalDate cycleEndDate;
}