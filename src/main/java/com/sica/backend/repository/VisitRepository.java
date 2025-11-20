package com.sica.backend.repository;

import com.sica.backend.entity.Visit;
import com.sica.backend.entity.VisitStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, String> {
    List<Visit> findByStatus(VisitStatus status);
    List<Visit> findByVisitDatetimeBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT v FROM Visit v WHERE v.status = :status AND v.visitDatetime BETWEEN :start AND :end")
    List<Visit> findByStatusAndVisitDatetimeBetween(VisitStatus status, LocalDateTime start, LocalDateTime end);

    @Query("SELECT v FROM Visit v WHERE LOWER(v.visitorName) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Visit> searchByVisitorName(String query);
}
