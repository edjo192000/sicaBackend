package com.sica.backend.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("admin123: " + encoder.encode("admin123"));
        System.out.println("prof123: " + encoder.encode("prof123"));
        System.out.println("est123: " + encoder.encode("est123"));
    }
}
