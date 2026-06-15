package com.student.management.service;

import com.student.management.model.RefreshToken;
import com.student.management.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    public RefreshTokenService(
            RefreshTokenRepository repository) {

        this.repository = repository;
    }

    public RefreshToken createToken(
            String username) {

        RefreshToken token =
                new RefreshToken();

        token.setToken(
                UUID.randomUUID()
                        .toString());

        token.setUsername(
                username);

        token.setExpiryDate(
                LocalDateTime.now()
                        .plusDays(7));

        repository.save(token);

        return token;
    }

    public RefreshToken validateToken(
            String tokenValue) {

        RefreshToken token =
                repository.findByToken(
                        tokenValue);

        if (token == null) {

            throw new RuntimeException(
                    "Invalid Refresh Token");
        }

        if (token.getExpiryDate()
                .isBefore(
                        LocalDateTime.now())) {

            throw new RuntimeException(
                    "Refresh Token Expired");
        }

        return token;
    }

    public void deleteToken(
            String token) {

        repository.deleteByToken(
                token);
    }
}