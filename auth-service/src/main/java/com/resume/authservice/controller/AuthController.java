package com.resume.authservice.controller;

import com.resume.authservice.config.UserServiceClient;
import com.resume.authservice.dto.UserDto;
import com.resume.authservice.request.LoginRequest;
import com.resume.authservice.dto.LoginResponse;
import com.resume.authservice.service.AuthService;
import com.resume.authservice.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserServiceClient userServiceClient;

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
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getPassword()
                    )
            );

            UserDto user = userServiceClient.getUserByEmail(loginRequest.getEmail());
            String token = jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
            return ResponseEntity.ok(Map.of("token", token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}