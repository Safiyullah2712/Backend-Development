package com.student.management.controller;

import org.springframework.web.bind.annotation.*;

import com.student.management.model.UserStatus;
import com.student.management.repository.UserStatusRepository;

@RestController
@RequestMapping("/status")
public class UserStatusController {

    private final UserStatusRepository repository;

    public UserStatusController(
            UserStatusRepository repository) {

        this.repository = repository;
    }

    @GetMapping("/{userId}")
    public UserStatus getStatus(
            @PathVariable Integer userId){

        return repository.findById(userId)
                .orElse(null);
    }
}