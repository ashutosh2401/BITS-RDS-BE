package com.resume.authservice.service;

import com.resume.authservice.util.JwtUtil;
import com.resume.authservice.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String generateToken(LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
//        );

        return jwtUtil.generateToken(loginRequest.getEmail());
    }

//    public String register(RegisterRequest request) {
//        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//            throw new RuntimeException("User already exists");
//        }
//
//        User user = User.builder()
//                .employeeId(request.getEmployeeId())
//                .username(request.getUsername())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role("USER")
//                .build();
//
//        userRepository.save(user);
//        return "User registered successfully";
//    }

//    public String login(LoginRequest request) {
////        Optional<User> user = userRepository.findByEmail(request.getEmail());
////
////        if (user.isPresent() && passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
////            return jwtUtil.generateToken(user.get().getEmail());
////        }
////        throw new RuntimeException("Invalid credentials");
//        if ("admin@example.com".equals(request.getEmail()) && "password".equals(request.getPassword())) {
//            String token = jwtUtil.generateToken(request.getEmail());
////            return new AuthResponse(token);
//            return "good";
//        }
////        return new AuthResponse("Invalid credentials");
//        return "Invalid credentials";
//    }


}