package com.sica.backend.repository

import com.sica.backend.entity.Justification
import com.sica.backend.entity.JustificationStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface JustificationRepository : JpaRepository<Justification, String> {
    fun findByStudentPersonId(studentId: String): List<Justification>
    fun findByStatus(status: JustificationStatus): List<Justification>
    fun findByAttendanceId(attendanceId: String): Justification?
    
    @Query("SELECT j FROM Justification j WHERE j.professor.id = :professorId")
    fun findByProfessorId(professorId: String): List<Justification>
    
    @Query("SELECT j FROM Justification j WHERE j.professor.id = :professorId AND j.status = :status")
    fun findByProfessorIdAndStatus(professorId: String, status: JustificationStatus): List<Justification>
}
