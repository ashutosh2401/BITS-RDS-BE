package com.resume.userservice.user.controller;

import com.resume.userservice.user.dto.LoginRequest;
import com.resume.userservice.user.dto.LoginResponse;
import com.resume.userservice.user.dto.RegisterRequest;
import com.resume.userservice.user.dto.RegisterResponse;
import com.resume.userservice.user.entity.User;
import com.resume.userservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/welcome")
    public String welcome() {
        System.out.println("welcome");
        return "Welcome this endpoint is for user service";
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        System.out.println("request is " +request);

        // Check if user already exists
        Optional<User> existingUser = userService.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Email is already registered.");
        }

        // Save user to DB
        RegisterResponse response = userService.save(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = userService.loginUser(loginRequest);
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", response.getToken())
                .httpOnly(true)  // ✅ Prevent JavaScript access (XSS protection)
                .secure(true)  // ✅ Send only over HTTPS
                .path("/")  // ✅ Available for all routes
                .maxAge(Duration.ofHours(1))  // ✅ Expiry time: 1 hour
                .sameSite("Strict")  // ✅ Protect against CSRF attacks
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(response);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest) {
//        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(
//                AUTH_SERVICE_URL + "/login", authRequest, AuthResponse.class);
//
//        return ResponseEntity.ok(response.getBody());
//    }


}