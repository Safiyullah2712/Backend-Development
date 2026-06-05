package com.student.management.service;

import com.student.management.dto.LoginRequest;
import com.student.management.dto.RegisterRequest;
import com.student.management.model.Role;
import com.student.management.model.User;
import com.student.management.repository.UserRepository;
import com.student.management.security.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repository;

    private final JwtUtil jwtUtil;

    public AuthService(
            UserRepository repository,
            JwtUtil jwtUtil) {

        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    public String register(
            RegisterRequest request) {

        if (repository.existsByUsername(
                request.getUsername())) {

            return "User already exists";
        }

        User user =
                new User(
                        request.getUsername(),
                        request.getPassword(),
                        Role.valueOf(
                                request.getRole()));

        repository.save(user);

        return "User Registered";
    }

    public String login(
            LoginRequest request) {

        User user =
                repository.findByUsername(
                        request.getUsername());

        if (user == null) {

            return "Invalid Username";
        }

        if (!user.getPassword()
                .equals(
                        request.getPassword())) {

            return "Invalid Password";
        }

        return jwtUtil.generateToken(
                user.getUsername(),
                user.getRole().name());
    }
}