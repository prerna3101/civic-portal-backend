package com.example.civicportal.service;

import com.example.civicportal.dto.AuthRequest;
import com.example.civicportal.dto.RegisterRequest;

public interface AuthService {

    String login(AuthRequest request);

    void register(RegisterRequest request);
}

