package com.sica.backend.repository

import com.sica.backend.entity.Person
import com.sica.backend.entity.PersonType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PersonRepository : JpaRepository<Person, String> {
    fun findByEmail(email: String): Optional<Person>
    fun findByType(type: PersonType): List<Person>
    fun findByActiveTrue(): List<Person>
    fun findByTypeAndActiveTrue(type: PersonType): List<Person>
}
