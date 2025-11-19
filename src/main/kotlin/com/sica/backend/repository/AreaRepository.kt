package com.sica.backend.repository

import com.sica.backend.entity.Area
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AreaRepository : JpaRepository<Area, String> {
    fun findByActiveTrue(): List<Area>
}
