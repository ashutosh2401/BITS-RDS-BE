package com.resume.authservice.controller;

import com.resume.authservice.dto.LoginRequest;
import com.resume.authservice.dto.LoginResponse;
import com.resume.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is for auth service";
    }

    @PostMapping("/generateToken")
    public ResponseEntity<LoginResponse> generateToken(@RequestBody LoginRequest loginRequest) {
        String token = authService.generateToken(loginRequest);
        return ResponseEntity.ok(new LoginResponse(token));
    }
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
//        return ResponseEntity.ok(authService.register(request));
//    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
//        return ResponseEntity.ok(authService.login(request));
//    }
}