package com.sica.backend.repository;

import com.sica.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findByEmployeeNumber(String employeeNumber);
    Boolean existsByEmployeeNumber(String employeeNumber);
}
