System Design Q & A:

1. How would you scale this system if it needed to support 500 concurrent managers
   running reports during performance season? 

Solution: 
    Read replicas — Route all GET requests to read-only PostgreSQL replicas. Keep the primary for writes. 500 concurrent reads easily handled with 2–3 replicas.
    Horizontal scaling — Run 3–4 Spring Boot instances behind a load balancer. The app is stateless, so this is trivial.
    Rate limiting — Cap expensive endpoints (like /cycles/{id}/summary) per user to prevent one manager from drowning the system.

2.  If a GET /cycles/{id}/summary query starts getting slow at 100k+ reviews, what
    would you do? 

Solution:
    Materialized view — Pre-compute avg_rating, top_performer, goal_counts per cycle. Refresh on each new review or nightly. Endpoint becomes a simple lookup.
    Covering index — Composite index on (review_cycle_id, employee_id, rating) for index-only scans.

3.  Where would you add caching, and what would you cache?

Solution:
    Redis — cycle summaries: Pre-computed JSON, 5 minute TTL.
    Redis — filtered employee lists: Department + rating queries, 2 minute TTL.
    App-level — cycle metadata: Cycle names and dates, 1 hour TTL.
    Invalidation: Evict cycle summary key whenever a review or goal is submitted for that cycle.
    Skip caching: Individual review details — too volatile, low hit rate.


