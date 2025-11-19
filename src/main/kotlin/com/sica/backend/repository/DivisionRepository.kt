package com.sica.backend.repository

import com.sica.backend.entity.Division
import com.sica.backend.entity.Level
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DivisionRepository : JpaRepository<Division, String> {
    fun findByLevel(level: Level): List<Division>
    fun findByActiveTrue(): List<Division>
    fun findByLevelAndActiveTrue(level: Level): List<Division>
}
