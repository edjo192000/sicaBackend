package com.sica.backend.repository

import com.sica.backend.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmployeeRepository : JpaRepository<Employee, String> {
    fun findByEmployeeNumber(employeeNumber: String): Optional<Employee>
    fun existsByEmployeeNumber(employeeNumber: String): Boolean
}
