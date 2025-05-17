package com.resume.userservice.auth.service;

import com.resume.userservice.auth.request.LoginRequest;
import com.resume.userservice.auth.request.RegisterRequest;
import org.springframework.http.ResponseCookie;

public interface AuthService {
    String register(RegisterRequest registerRequest);
    String login(LoginRequest loginRequest);
    ResponseCookie getJwtCookie(String token);
}
