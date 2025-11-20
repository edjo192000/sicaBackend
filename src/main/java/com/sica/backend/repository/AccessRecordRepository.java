package com.sica.backend.repository;

import com.sica.backend.entity.AccessRecord;
import com.sica.backend.entity.AccessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccessRecordRepository extends JpaRepository<AccessRecord, String> {
    List<AccessRecord> findByUserId(String userId);
    List<AccessRecord> findByDate(LocalDate date);
    List<AccessRecord> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT ar FROM AccessRecord ar WHERE ar.user.id = :userId AND ar.date BETWEEN :startDate AND :endDate")
    List<AccessRecord> findByUserIdAndDateBetween(String userId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT ar FROM AccessRecord ar WHERE ar.user.id = :userId AND ar.date BETWEEN :startDate AND :endDate AND ar.type = :type")
    List<AccessRecord> findByUserIdAndDateBetweenAndType(String userId, LocalDate startDate, LocalDate endDate, AccessType type);
}
