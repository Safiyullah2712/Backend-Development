package com.student.management.service;

import com.student.management.dto.AuthResponse;
import com.student.management.dto.LoginRequest;
import com.student.management.dto.RegisterRequest;
import com.student.management.model.RefreshToken;
import com.student.management.model.Role;
import com.student.management.model.User;
import com.student.management.model.UserStatus;
import com.student.management.repository.UserRepository;
import com.student.management.repository.UserStatusRepository;
import com.student.management.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository repository;
    private final UserStatusRepository userStatusRepository;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public AuthService(
            UserRepository repository,
            UserStatusRepository userStatusRepository,
            JwtUtil jwtUtil,
            RefreshTokenService refreshTokenService) {

        this.repository = repository;
        this.userStatusRepository = userStatusRepository;
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
    }

    public String register(RegisterRequest request) {

        if (repository.existsByUsername(
                request.getUsername())) {

            return "User already exists";
        }

        User user = new User(
                request.getUsername(),
                request.getPassword(),
                Role.valueOf(request.getRole()));

        repository.save(user);

        return "User Registered Successfully";
    }

    public AuthResponse login(LoginRequest request) {

        User user =
                repository.findByUsername(
                        request.getUsername());

        if (user == null) {
            throw new RuntimeException(
                    "Invalid Username");
        }

        if (!user.getPassword()
                .equals(request.getPassword())) {

            throw new RuntimeException(
                    "Invalid Password");
        }

        UserStatus status =
                userStatusRepository
                        .findById(
                                user.getId())
                        .orElse(new UserStatus());

        status.setUser(user);

        status.setOnline(true);

        status.setLastSeen(
                LocalDateTime.now());

        userStatusRepository.save(status);

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
            String refreshTokenValue) {

        RefreshToken refreshToken =
                refreshTokenService
                        .validateToken(
                                refreshTokenValue);

        User user =
                repository.findByUsername(
                        refreshToken.getUsername());

        UserStatus status =
                userStatusRepository
                        .findById(
                                user.getId())
                        .orElse(null);

        if(status != null){

            status.setOnline(false);

            status.setLastSeen(
                    LocalDateTime.now());

            userStatusRepository.save(
                    status);
        }

        refreshTokenService
                .deleteToken(
                        refreshTokenValue);

        return "Logged Out Successfully";
    }
}