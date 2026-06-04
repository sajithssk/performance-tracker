INSERT INTO review_cycles (name, start_date, end_date) VALUES
                                                           ('Q1 2025', '2025-01-01', '2025-03-31'),
                                                           ('Q2 2025', '2025-04-01', '2025-06-30');

INSERT INTO employees (name, department, role, joining_date) VALUES
                                                                 ('Alice Johnson', 'Engineering', 'Senior Developer', '2022-03-15'),
                                                                 ('Bob Smith', 'Engineering', 'Developer', '2023-06-01'),
                                                                 ('Carol White', 'Sales', 'Sales Manager', '2021-11-20');

INSERT INTO performance_reviews (employee_id, review_cycle_id, rating, reviewer_notes, submitted_at) VALUES
                                                                                                         (1, 1, 5, 'Excellent leadership on Project X', '2025-04-01T10:00:00'),
                                                                                                         (2, 1, 3, 'Meets expectations, needs mentoring', '2025-04-02T10:00:00'),
                                                                                                         (1, 2, 4, 'Solid progress this quarter', '2025-07-01T10:00:00'),
                                                                                                         (2, 2, 4, 'Improved significantly', '2025-07-02T10:00:00');

INSERT INTO goals (employee_id, review_cycle_id, title, status) VALUES
                                                                    (1, 1, 'Ship Project X', 'COMPLETED'),
                                                                    (1, 1, 'Mentor 2 juniors', 'COMPLETED'),
                                                                    (2, 1, 'Close 10 critical tickets', 'MISSED'),
                                                                    (1, 2, 'Lead architecture review', 'PENDING'),
                                                                    (2, 2, 'Write 5 tech docs', 'COMPLETED');