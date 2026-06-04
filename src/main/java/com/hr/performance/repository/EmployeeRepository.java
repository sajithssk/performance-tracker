package com.hr.performance.repository;

import com.hr.performance.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
List<Employee> findByDepartment(String department);
}