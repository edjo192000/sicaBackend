package com.sica.backend.repository

import com.sica.backend.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StudentRepository : JpaRepository<Student, String> {
    fun findByEnrollmentNumber(enrollmentNumber: String): Optional<Student>
    fun existsByEnrollmentNumber(enrollmentNumber: String): Boolean
}
