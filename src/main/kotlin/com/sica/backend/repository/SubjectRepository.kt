package com.sica.backend.repository

import com.sica.backend.entity.Subject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SubjectRepository : JpaRepository<Subject, String> {
    fun findByCode(code: String): Optional<Subject>
    fun findByProfessorId(professorId: String): List<Subject>
    fun findByActiveTrueAndProfessorId(professorId: String): List<Subject>
    
    @Query("SELECT s FROM Subject s JOIN SubjectEnrollment se ON se.subject.id = s.id WHERE se.student.personId = :studentId AND s.active = true")
    fun findByStudentId(studentId: String): List<Subject>
}
