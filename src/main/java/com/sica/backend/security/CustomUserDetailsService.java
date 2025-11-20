package com.sica.backend.security;

import com.sica.backend.entity.User;
import com.sica.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "users", key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        if (!user.getActive()) {
            throw new UsernameNotFoundException("Usuario inactivo: " + username);
        }

        return UserPrincipal.create(user);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "users", key = "#id")
    public UserDetails loadUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ID: " + id));

        if (!user.getActive()) {
            throw new UsernameNotFoundException("Usuario inactivo con ID: " + id);
        }

        return UserPrincipal.create(user);
    }
}
