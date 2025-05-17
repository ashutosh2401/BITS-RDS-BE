package com.resume.userservice.auth.service.impl;

import com.resume.userservice.auth.request.LoginRequest;
import com.resume.userservice.auth.request.RegisterRequest;
//import com.resume.userservice.auth.response.LoginResponse;
import com.resume.userservice.auth.service.AuthService;
import com.resume.userservice.auth.util.JwtUtil;
import com.resume.userservice.user.dto.UserDto;
import com.resume.userservice.user.entity.User;
import com.resume.userservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterRequest request) {
        Optional<User> existingUser = userService.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new NoSuchElementException("Email is already registered.");
        }
        User savedUser = userService.save(request);
        return jwtUtil.generateToken(savedUser.getEmail());
    }

    @Override
    public String login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()
                )
        );
        User user = userService.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + loginRequest.getEmail()));
        return jwtUtil.generateToken(user.getEmail());
    }

    @Override
    public ResponseCookie getJwtCookie(String token) {
        return ResponseCookie.from("jwt", token)
                .httpOnly(true)  // Prevent JavaScript access (XSS protection)
                .secure(true)  // Send only over HTTPS
                .path("/")  // Available for all routes
                .maxAge(Duration.ofHours(1))  // Expiry time: 1 hour
                .sameSite("Strict")  // Protect against CSRF attacks
                .build();
    }

}