package com.sica.backend.service;

import com.sica.backend.dto.AuthRequest;
import com.sica.backend.dto.AuthResponse;
import com.sica.backend.entity.*;
import com.sica.backend.exception.ResourceNotFoundException;
import com.sica.backend.repository.*;
import com.sica.backend.security.JwtTokenProvider;
import com.sica.backend.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final EmployeeRepository employeeRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        // Actualizar último login
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        // Obtener matrícula o número de empleado
        String matricula = null;
        String numeroEmpleado = null;

        switch (user.getRole()) {
            case ESTUDIANTE:
                matricula = studentRepository.findById(user.getPerson().getId())
                        .map(Student::getEnrollmentNumber)
                        .orElse(null);
                break;
            case PROFESOR:
            case ADMINISTRADOR:
                numeroEmpleado = employeeRepository.findById(user.getPerson().getId())
                        .map(Employee::getEmployeeNumber)
                        .orElse(null);
                break;
        }

        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        return new AuthResponse(
                user.getId(),
                user.getUsername(),
                user.getPerson().getFullName(),
                user.getRole().name(),
                user.getPerson().getEmail(),
                user.getPerson().getPhotoUrl(),
                token,
                refreshToken
        );
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }
}
