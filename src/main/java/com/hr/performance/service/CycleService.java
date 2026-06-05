package com.hr.performance.service;

import com.hr.performance.dto.CycleSummaryDTO;
import com.hr.performance.dto.TopPerformerDTO;
import com.hr.performance.entity.Goal;
import com.hr.performance.entity.ReviewCycle;
import com.hr.performance.repository.GoalRepository;
import com.hr.performance.repository.PerformanceReviewRepository;
import com.hr.performance.repository.ReviewCycleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CycleService {

    @Autowired
    private ReviewCycleRepository cycleRepository;

    @Autowired
    private PerformanceReviewRepository reviewRepository;

    @Autowired
    private GoalRepository goalRepository;

    public CycleSummaryDTO getCycleSummary(Long cycleId) {
        ReviewCycle cycle = cycleRepository.findById(cycleId)
                .orElseThrow(() -> new EntityNotFoundException("Cycle not found"));

        Double avgRating = reviewRepository.findAverageRatingByCycleId(cycleId);
        if (avgRating == null) avgRating = 0.0;

        TopPerformerDTO topPerformer = null;
        List<Object[]> topList = reviewRepository.findTopPerformerByCycle(cycleId, PageRequest.of(0, 1));
        if (!topList.isEmpty()) {
            Object[] row = topList.get(0);
            topPerformer = new TopPerformerDTO(
                    ((Number) row[0]).longValue(),
                    (String) row[1],
                    ((Number) row[2]).doubleValue()
            );
        }

        Long completed = goalRepository.countByReviewCycleIdAndStatus(cycleId, Goal.GoalStatus.COMPLETED);
        Long missed = goalRepository.countByReviewCycleIdAndStatus(cycleId, Goal.GoalStatus.MISSED);

        CycleSummaryDTO summary = new CycleSummaryDTO();
        summary.setCycleId(cycle.getId());
        summary.setCycleName(cycle.getName());
        summary.setAverageRating(avgRating);
        summary.setTopPerformer(topPerformer);
        summary.setCompletedGoals(completed != null ? completed : 0L);
        summary.setMissedGoals(missed != null ? missed : 0L);

        return summary;
    }
}