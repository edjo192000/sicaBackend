package com.sica.backend.repository;

import com.sica.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByEnrollmentNumber(String enrollmentNumber);
    Boolean existsByEnrollmentNumber(String enrollmentNumber);
}
