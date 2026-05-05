package com.example.civicportal.service;

import com.example.civicportal.dto.AuthRequest;

public interface AuthService {
    String login(AuthRequest request);
}
