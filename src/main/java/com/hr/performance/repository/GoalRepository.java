package com.hr.performance.repository;

import com.hr.performance.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
Long countByReviewCycleIdAndStatus(Long reviewCycleId, Goal.GoalStatus status);
}