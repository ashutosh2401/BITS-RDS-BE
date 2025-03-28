package com.resume.userservice.service;

import com.resume.userservice.dto.LoginRequest;
import com.resume.userservice.dto.LoginResponse;
import com.resume.userservice.dto.RegisterRequest;
import com.resume.userservice.dto.RegisterResponse;
import com.resume.userservice.entity.User;
import com.resume.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private WebClient.Builder webClientBuilder;


    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public RegisterResponse save(RegisterRequest request) {
        // Encode password before mapping
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        // Convert DTO to Entity using ModelMapper
        User user = modelMapper.map(request, User.class);

        // Set default role
        user.setRole("USER");

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, RegisterResponse.class);
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        // Check if user exists
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Call auth-service to get JWT token
        String authServiceUrl = "http://localhost:8082/api/v1/auth/generateToken";

        return webClientBuilder.build()
                .post()
                .uri(authServiceUrl)
                .bodyValue(loginRequest)
                .retrieve()
                .bodyToMono(LoginResponse.class)
                .block(); // Blocking call (use async if needed)
    }
}
