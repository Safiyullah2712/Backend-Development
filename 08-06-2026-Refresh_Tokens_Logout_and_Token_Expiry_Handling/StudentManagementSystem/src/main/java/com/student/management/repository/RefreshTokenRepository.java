package com.student.management.repository;

import com.student.management.model.RefreshToken;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RefreshTokenRepository {

    private final Map<String, RefreshToken>
            refreshTokenStore =
            new HashMap<>();

    public void save(
            RefreshToken token) {

        refreshTokenStore.put(
                token.getToken(),
                token);
    }

    public RefreshToken findByToken(
            String token) {

        return refreshTokenStore.get(token);
    }

    public void deleteByToken(
            String token) {

        refreshTokenStore.remove(token);
    }
}