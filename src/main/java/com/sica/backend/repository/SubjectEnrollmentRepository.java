package com.sica.backend.repository;

import com.sica.backend.entity.SubjectEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectEnrollmentRepository extends JpaRepository<SubjectEnrollment, String> {
    List<SubjectEnrollment> findBySubjectId(String subjectId);
    List<SubjectEnrollment> findByStudentPersonId(String studentId);

    @Query("SELECT se FROM SubjectEnrollment se WHERE se.subject.id = :subjectId AND se.student.personId = :studentId")
    SubjectEnrollment findBySubjectIdAndStudentId(String subjectId, String studentId);
}
