package com.example.civicportal.controller;

import com.example.civicportal.dto.AuthRequest;
import com.example.civicportal.dto.AuthResponse;
import com.example.civicportal.dto.RegisterRequest;
import com.example.civicportal.entity.User;
import com.example.civicportal.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    // ✅ LOGIN (already correct)
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return new AuthResponse(service.login(request));
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        service.register(request);
        return "User registered successfully";
    }
}