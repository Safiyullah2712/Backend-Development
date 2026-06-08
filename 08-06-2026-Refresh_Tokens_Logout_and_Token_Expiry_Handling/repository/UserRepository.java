package com.student.management.repository;

import com.student.management.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private final Map<String, User> users =
            new HashMap<>();

    public void save(User user) {

        users.put(
                user.getUsername(),
                user);
    }

    public User findByUsername(
            String username) {

        return users.get(username);
    }

    public boolean existsByUsername(
            String username) {

        return users.containsKey(
                username);
    }
}