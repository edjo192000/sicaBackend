package com.sica.backend.service

import com.sica.backend.dto.AuthRequest
import com.sica.backend.dto.AuthResponse
import com.sica.backend.entity.*
import com.sica.backend.exception.BadRequestException
import com.sica.backend.exception.ResourceNotFoundException
import com.sica.backend.repository.*
import com.sica.backend.security.JwtTokenProvider
import com.sica.backend.security.UserPrincipal
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val studentRepository: StudentRepository,
    private val employeeRepository: EmployeeRepository,
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun login(request: AuthRequest): AuthResponse {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.username, request.password)
        )

        SecurityContextHolder.getContext().authentication = authentication
        val token = jwtTokenProvider.generateToken(authentication)

        val userPrincipal = authentication.principal as UserPrincipal
        val user = userRepository.findById(userPrincipal.id)
            .orElseThrow { ResourceNotFoundException("Usuario no encontrado") }

        // Actualizar último login
        user.lastLogin = LocalDateTime.now()
        userRepository.save(user)

        // Obtener matrícula o número de empleado
        var matricula: String? = null
        var numeroEmpleado: String? = null

        when (user.role) {
            Role.ESTUDIANTE -> {
                matricula = studentRepository.findById(user.person.id!!)
                    .map { it.enrollmentNumber }
                    .orElse(null)
            }
            Role.PROFESOR, Role.ADMINISTRADOR -> {
                numeroEmpleado = employeeRepository.findById(user.person.id!!)
                    .map { it.employeeNumber }
                    .orElse(null)
            }
        }

        val refreshToken = jwtTokenProvider.generateRefreshToken(user.id!!)

        return AuthResponse(
            id = user.id!!,
            username = user.username,
            name = user.person.fullName,
            role = user.role.name,
            email = user.person.email,
            foto = user.person.photoUrl,
            token = token,
            refreshToken = refreshToken
        )
    }

    fun getCurrentUser(): User {
        val authentication = SecurityContextHolder.getContext().authentication
        val userPrincipal = authentication.principal as UserPrincipal
        return userRepository.findById(userPrincipal.id)
            .orElseThrow { ResourceNotFoundException("Usuario no encontrado") }
    }
}
