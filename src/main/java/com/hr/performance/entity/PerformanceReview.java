package com.hr.performance.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "performance_reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_cycle_id", nullable = false)
    private ReviewCycle reviewCycle;

    @Column(nullable = false)
    private Integer rating;

    @Column(name = "reviewer_notes")
    private String reviewerNotes;

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;
}