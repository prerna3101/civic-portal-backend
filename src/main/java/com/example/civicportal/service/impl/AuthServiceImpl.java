package com.example.civicportal.service.impl;

import com.example.civicportal.dto.AuthRequest;
import com.example.civicportal.dto.RegisterRequest;
import com.example.civicportal.entity.User;
import com.example.civicportal.enums.Role;
import com.example.civicportal.repository.UserRepository;
import com.example.civicportal.security.JwtUtil;
import com.example.civicportal.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    // ✅ LOGIN
    @Override
    public String login(AuthRequest request) {

        User user = repo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // ✅ FIX: send BOTH username + role
        return jwtUtil.generateToken(
                user.getUsername(),
                user.getRole().name()
        );
    }

    // ✅ REGISTER (UPDATED)
    @Override
    public void register(RegisterRequest request) {

        // check if user already exists
        if (repo.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Role.USER); // default role

        repo.save(user);
    }
}