package com.sica.backend.repository

import com.sica.backend.entity.AccessRecord
import com.sica.backend.entity.AccessType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface AccessRecordRepository : JpaRepository<AccessRecord, String> {
    fun findByUserId(userId: String): List<AccessRecord>
    fun findByDate(date: LocalDate): List<AccessRecord>
    fun findByDateBetween(startDate: LocalDate, endDate: LocalDate): List<AccessRecord>
    
    @Query("SELECT ar FROM AccessRecord ar WHERE ar.user.id = :userId AND ar.date BETWEEN :startDate AND :endDate")
    fun findByUserIdAndDateBetween(userId: String, startDate: LocalDate, endDate: LocalDate): List<AccessRecord>
    
    @Query("SELECT ar FROM AccessRecord ar WHERE ar.user.id = :userId AND ar.date BETWEEN :startDate AND :endDate AND ar.type = :type")
    fun findByUserIdAndDateBetweenAndType(userId: String, startDate: LocalDate, endDate: LocalDate, type: AccessType): List<AccessRecord>
}
