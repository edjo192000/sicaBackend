package com.sica.backend.repository

import com.sica.backend.entity.Attendance
import com.sica.backend.entity.AttendanceStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface AttendanceRepository : JpaRepository<Attendance, String> {
    fun findByStudentPersonId(studentId: String): List<Attendance>
    fun findBySubjectId(subjectId: String): List<Attendance>
    fun findBySubjectIdAndDate(subjectId: String, date: LocalDate): List<Attendance>
    
    @Query("SELECT a FROM Attendance a WHERE a.student.personId = :studentId AND a.date BETWEEN :startDate AND :endDate")
    fun findByStudentIdAndDateBetween(studentId: String, startDate: LocalDate, endDate: LocalDate): List<Attendance>
    
    @Query("SELECT a FROM Attendance a WHERE a.student.personId = :studentId AND a.status = :status AND a.justified = false")
    fun findByStudentIdAndStatusAndJustifiedFalse(studentId: String, status: AttendanceStatus): List<Attendance>
    
    @Query("SELECT a FROM Attendance a WHERE a.student.personId = :studentId AND a.subject.id = :subjectId")
    fun findByStudentIdAndSubjectId(studentId: String, subjectId: String): List<Attendance>
}
