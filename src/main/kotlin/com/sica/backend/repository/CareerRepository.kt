package com.sica.backend.repository

import com.sica.backend.entity.Career
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CareerRepository : JpaRepository<Career, String> {
    fun findByDivisionId(divisionId: String): List<Career>
    fun findByActiveTrue(): List<Career>
    fun findByDivisionIdAndActiveTrue(divisionId: String): List<Career>
}
