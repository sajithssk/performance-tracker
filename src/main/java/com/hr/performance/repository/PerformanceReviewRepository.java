package com.hr.performance.repository;

import com.hr.performance.dto.EmployeeReviewDTO;
import com.hr.performance.entity.PerformanceReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Long> {

@Query("SELECT new com.hr.performance.dto.EmployeeReviewDTO(" +
        "pr.id, pr.rating, pr.reviewerNotes, pr.submittedAt, " +
        "rc.id, rc.name, rc.startDate, rc.endDate) " +
        "FROM PerformanceReview pr " +
        "JOIN pr.reviewCycle rc " +
        "WHERE pr.employee.id = :employeeId " +
        "ORDER BY pr.submittedAt DESC")
List<EmployeeReviewDTO> findReviewsWithCycleByEmployeeId(@Param("employeeId") Long employeeId);

@Query("SELECT AVG(pr.rating) FROM PerformanceReview pr WHERE pr.reviewCycle.id = :cycleId")
Double findAverageRatingByCycleId(@Param("cycleId") Long cycleId);

@Query("SELECT e.id, e.name, AVG(pr.rating) FROM PerformanceReview pr JOIN pr.employee e " +
        "WHERE pr.reviewCycle.id = :cycleId " +
        "GROUP BY e.id, e.name " +
        "ORDER BY AVG(pr.rating) DESC")
List<Object[]> findTopPerformerByCycle(@Param("cycleId") Long cycleId, Pageable pageable);

@Query("SELECT pr.employee.id, AVG(pr.rating) FROM PerformanceReview pr GROUP BY pr.employee.id")
List<Object[]> calculateAllAverageRatings();
}