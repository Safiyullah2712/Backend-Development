package com.student.management.repository;

import com.student.management.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusRepository
        extends JpaRepository<UserStatus,Integer> {
}