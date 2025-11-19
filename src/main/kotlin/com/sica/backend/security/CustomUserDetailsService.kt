package com.sica.backend.security

import com.sica.backend.repository.UserRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    @Transactional(readOnly = true)
    @Cacheable(value = ["users"], key = "#username")
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("Usuario no encontrado: $username") }

        if (!user.active) {
            throw UsernameNotFoundException("Usuario inactivo: $username")
        }

        return UserPrincipal.create(user)
    }

    @Transactional(readOnly = true)
    @Cacheable(value = ["users"], key = "#id")
    fun loadUserById(id: String): UserDetails {
        val user = userRepository.findById(id)
            .orElseThrow { UsernameNotFoundException("Usuario no encontrado con ID: $id") }

        if (!user.active) {
            throw UsernameNotFoundException("Usuario inactivo con ID: $id")
        }

        return UserPrincipal.create(user)
    }
}
