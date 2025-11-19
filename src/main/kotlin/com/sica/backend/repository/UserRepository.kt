package com.sica.backend.repository

import com.sica.backend.entity.Role
import com.sica.backend.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, String> {
    fun findByUsername(username: String): Optional<User>
    fun findByRole(role: Role): List<User>
    fun findByActiveTrue(): List<User>
    fun existsByUsername(username: String): Boolean
}
