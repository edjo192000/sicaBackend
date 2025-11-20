package com.sica.backend.repository;

import com.sica.backend.entity.Attendance;
import com.sica.backend.entity.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, String> {
    List<Attendance> findByStudentPersonId(String studentId);
    List<Attendance> findBySubjectId(String subjectId);
    List<Attendance> findBySubjectIdAndDate(String subjectId, LocalDate date);

    @Query("SELECT a FROM Attendance a WHERE a.student.personId = :studentId AND a.date BETWEEN :startDate AND :endDate")
    List<Attendance> findByStudentIdAndDateBetween(String studentId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT a FROM Attendance a WHERE a.student.personId = :studentId AND a.status = :status AND a.justified = false")
    List<Attendance> findByStudentIdAndStatusAndJustifiedFalse(String studentId, AttendanceStatus status);

    @Query("SELECT a FROM Attendance a WHERE a.student.personId = :studentId AND a.subject.id = :subjectId")
    List<Attendance> findByStudentIdAndSubjectId(String studentId, String subjectId);
}
