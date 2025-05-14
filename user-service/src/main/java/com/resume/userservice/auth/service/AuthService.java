package com.resume.userservice.auth.service;

import com.resume.userservice.auth.request.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest registerRequest);
}
