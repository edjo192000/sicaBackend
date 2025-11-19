package com.sica.backend.repository

import com.sica.backend.entity.Visit
import com.sica.backend.entity.VisitStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface VisitRepository : JpaRepository<Visit, String> {
    fun findByStatus(status: VisitStatus): List<Visit>
    fun findByVisitDatetimeBetween(start: LocalDateTime, end: LocalDateTime): List<Visit>
    
    @Query("SELECT v FROM Visit v WHERE v.status = :status AND v.visitDatetime BETWEEN :start AND :end")
    fun findByStatusAndVisitDatetimeBetween(status: VisitStatus, start: LocalDateTime, end: LocalDateTime): List<Visit>
    
    @Query("SELECT v FROM Visit v WHERE LOWER(v.visitorName) LIKE LOWER(CONCAT('%', :query, '%'))")
    fun searchByVisitorName(query: String): List<Visit>
}
