package com.resume.userservice.auth.service.impl;

import com.resume.userservice.auth.request.LoginRequest;
import com.resume.userservice.auth.request.RegisterRequest;
import com.resume.userservice.auth.service.AuthService;
import com.resume.userservice.auth.util.JwtUtil;
import com.resume.userservice.user.entity.User;
import com.resume.userservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

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
        userService.save(request);
        return "";
    }

    public String generateToken(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        return jwtUtil.generateToken(loginRequest.getEmail());
    }

//    public String login(LoginRequest request) {
//        if ("admin@example.com".equals(request.getEmail()) && "password".equals(request.getPassword())) {
//            String token = jwtUtil.generateToken(request.getEmail());
////            return new AuthResponse(token);
//            return "good";
//        }
////        return new AuthResponse("Invalid credentials");
//        return "Invalid credentials";
//    }
}