package com.student.management.repository;

import com.student.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Integer> {

    User findByUsername(
            String username);

    boolean existsByUsername(
            String username);
}