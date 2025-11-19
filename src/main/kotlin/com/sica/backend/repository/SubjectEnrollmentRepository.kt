package com.sica.backend.repository

import com.sica.backend.entity.SubjectEnrollment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SubjectEnrollmentRepository : JpaRepository<SubjectEnrollment, String> {
    fun findBySubjectId(subjectId: String): List<SubjectEnrollment>
    fun findByStudentPersonId(studentId: String): List<SubjectEnrollment>
    
    @Query("SELECT se FROM SubjectEnrollment se WHERE se.subject.id = :subjectId AND se.student.personId = :studentId")
    fun findBySubjectIdAndStudentId(subjectId: String, studentId: String): SubjectEnrollment?
}
