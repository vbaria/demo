package com.demo.employeeportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.employeeportal.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
