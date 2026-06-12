package com.student.management.repository;

import com.student.management.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSessionRepository
        extends JpaRepository<UserSession,Long> {

    List<UserSession> findByUserIdAndActive(
            Integer userId,
            Boolean active);
}