package com.sica.backend.repository;

import com.sica.backend.entity.Justification;
import com.sica.backend.entity.JustificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JustificationRepository extends JpaRepository<Justification, String> {
    List<Justification> findByStudentPersonId(String studentId);
    List<Justification> findByStatus(JustificationStatus status);
    Justification findByAttendanceId(String attendanceId);

    @Query("SELECT j FROM Justification j WHERE j.professor.id = :professorId")
    List<Justification> findByProfessorId(String professorId);

    @Query("SELECT j FROM Justification j WHERE j.professor.id = :professorId AND j.status = :status")
    List<Justification> findByProfessorIdAndStatus(String professorId, JustificationStatus status);
}
