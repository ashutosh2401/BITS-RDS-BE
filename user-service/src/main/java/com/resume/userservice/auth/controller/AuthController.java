package com.resume.userservice.auth.controller;

import com.resume.userservice.auth.request.LoginRequest;
import com.resume.userservice.auth.request.RegisterRequest;
//import com.resume.userservice.auth.service.AuthService;
import com.resume.userservice.auth.service.AuthService;
import com.resume.userservice.user.dto.UserDto;
import com.resume.userservice.user.entity.User;
import com.resume.userservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

//    @Autowired
//    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            authService.register(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
//        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("token", token));
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequest.getEmail(), loginRequest.getPassword()
//                    )
//            );
//            UserDto user = userServiceClient.getUserByEmail(loginRequest.getEmail());
//            String token = jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
//            return ResponseEntity.ok(Map.of("token", token));
//
//            LoginResponse response = userService.loginUser(loginRequest);
//            ResponseCookie jwtCookie = ResponseCookie.from("jwt", response.getToken())
//                    .httpOnly(true)  // ✅ Prevent JavaScript access (XSS protection)
//                    .secure(true)  // ✅ Send only over HTTPS
//                    .path("/")  // ✅ Available for all routes
//                    .maxAge(Duration.ofHours(1))  // ✅ Expiry time: 1 hour
//                    .sameSite("Strict")  // ✅ Protect against CSRF attacks
//                    .build();
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//                    .body(response);
//        } catch (AuthenticationException ex) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }
}