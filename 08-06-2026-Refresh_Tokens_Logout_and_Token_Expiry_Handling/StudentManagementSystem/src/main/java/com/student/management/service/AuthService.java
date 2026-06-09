package com.student.management.service;

import com.student.management.dto.AuthResponse;
import com.student.management.dto.LoginRequest;
import com.student.management.dto.RegisterRequest;
import com.student.management.model.RefreshToken;
import com.student.management.model.Role;
import com.student.management.model.User;
import com.student.management.repository.UserRepository;
import com.student.management.security.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repository;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public AuthService(
            UserRepository repository,
            JwtUtil jwtUtil,
            RefreshTokenService refreshTokenService) {

        this.repository = repository;
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
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

    public AuthResponse login(
            LoginRequest request) {

        User user =
                repository.findByUsername(
                        request.getUsername());

        if (user == null) {

            throw new RuntimeException(
                    "Invalid Username");
        }

        if (!user.getPassword()
                .equals(
                        request.getPassword())) {

            throw new RuntimeException(
                    "Invalid Password");
        }

        String accessToken =
                jwtUtil.generateToken(
                        user.getUsername(),
                        user.getRole().name());

        RefreshToken refreshToken =
                refreshTokenService
                        .createToken(
                                user.getUsername());

        return new AuthResponse(
                accessToken,
                refreshToken.getToken());
    }

    public AuthResponse refreshToken(
            String refreshTokenValue) {

        RefreshToken refreshToken =
                refreshTokenService
                        .validateToken(
                                refreshTokenValue);

        User user =
                repository.findByUsername(
                        refreshToken.getUsername());

        String accessToken =
                jwtUtil.generateToken(
                        user.getUsername(),
                        user.getRole().name());

        return new AuthResponse(
                accessToken,
                refreshToken.getToken());
    }

    public String logout(
            String refreshToken) {

        refreshTokenService
                .deleteToken(
                        refreshToken);

        return "Logged Out Successfully";
    }
}