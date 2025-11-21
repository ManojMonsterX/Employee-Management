package com.example.Employee_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Employee_Management.model.employee;

public interface EmployeeRepository extends JpaRepository<employee, Long> {

}
