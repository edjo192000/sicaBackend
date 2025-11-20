package com.sica.backend.repository;

import com.sica.backend.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    Optional<Subject> findByCode(String code);
    List<Subject> findByProfessorId(String professorId);
    List<Subject> findByActiveTrueAndProfessorId(String professorId);

    @Query("SELECT s FROM Subject s JOIN SubjectEnrollment se ON se.subject.id = s.id WHERE se.student.personId = :studentId AND s.active = true")
    List<Subject> findByStudentId(String studentId);
}
