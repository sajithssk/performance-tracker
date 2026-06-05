package com.hr.performance.service;

import com.hr.performance.dto.ReviewRequest;
import com.hr.performance.entity.Employee;
import com.hr.performance.entity.PerformanceReview;
import com.hr.performance.entity.ReviewCycle;
import com.hr.performance.repository.EmployeeRepository;
import com.hr.performance.repository.PerformanceReviewRepository;
import com.hr.performance.repository.ReviewCycleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewService {

    @Autowired
    private PerformanceReviewRepository reviewRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ReviewCycleRepository cycleRepository;

    public PerformanceReview submitReview(ReviewRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeId()).orElseThrow(() ->
                                new EntityNotFoundException("Employee not found"));
        ReviewCycle cycle = cycleRepository.findById(request.getCycleId()).orElseThrow(() -> new
                EntityNotFoundException("Review cycle not found"));

        if (request.getRating() == null || request.getRating() > 5) {
            throw  new IllegalArgumentException("Rating must br between 1 and 5");
        }

        PerformanceReview review = new PerformanceReview();
        review.setEmployee(employee);
        review.setReviewCycle(cycle);
        review.setRating(request.getRating());
        review.setReviewerNotes(request.getReviewerNotes());
        review.setSubmittedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }
}
