package com.hr.performance.dto;

import lombok.Data;

@Data
public class CycleSummaryDTO {

    private Long cycleId;
    private String cycleName;
    private Double averageRating;
    private TopPerformerDTO topPerformer;
    private Long completedGoals;
    private Long missedGoals;
}