package com.student.management.controller;

import com.student.management.dto.AuthResponse;
import com.student.management.dto.LoginRequest;
import com.student.management.dto.RefreshRequest;
import com.student.management.dto.RegisterRequest;
import com.student.management.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(
            AuthService service) {

        this.service = service;
    }

    @PostMapping("/register")
    public String register(
            @RequestBody
            RegisterRequest request) {

        return service.register(
                request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody
            LoginRequest request) {

        return service.login(
                request);
    }

    @PostMapping("/refresh")
    public AuthResponse refreshToken(
            @RequestBody
            RefreshRequest request) {

        return service.refreshToken(
                request.getRefreshToken());
    }

    @PostMapping("/logout")
    public String logout(
            @RequestBody
            RefreshRequest request) {

        return service.logout(
                request.getRefreshToken());
    }
}