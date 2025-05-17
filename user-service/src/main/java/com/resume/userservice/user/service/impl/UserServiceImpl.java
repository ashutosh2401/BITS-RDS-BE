package com.resume.userservice.user.service.impl;

import com.resume.userservice.auth.request.RegisterRequest;
import com.resume.userservice.organization.entity.Organization;
import com.resume.userservice.organization.repository.OrganizationRepository;
import com.resume.userservice.user.entity.User;
import com.resume.userservice.user.repository.UserRepository;
import com.resume.userservice.user.service.UserService;
import com.resume.userservice.vertical.entity.Vertical;
import com.resume.userservice.vertical.repository.VerticalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private VerticalRepository verticalRepository;


    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(RegisterRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        User user = modelMapper.map(request, User.class);

        Organization org = organizationRepository.findByOrgId(request.getOrgId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        Vertical vertical = verticalRepository.findByVerticalId(request.getVerticalId())
                .orElseThrow(() -> new RuntimeException("Vertical not found"));

        user.setOrganization(org);
        user.setVertical(vertical);
        user.setRole("USER");

        return userRepository.save(user);
//        return modelMapper.map(savedUser, RegisterResponse.class);
    }

//    @Override
//    public LoginResponse loginUser(LoginRequest loginRequest) {
//        // Check if user exists
//        User user = userRepository.findByEmail(loginRequest.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Call auth-service to get JWT token
//        String authServiceUrl = "http://localhost:8082/api/v1/auth/generateToken";
//
//        return webClientBuilder.build()
//                .post()
//                .uri(authServiceUrl)
//                .bodyValue(loginRequest)
//                .retrieve()
//                .bodyToMono(LoginResponse.class)
//                .block(); // Blocking call (use async if needed)
//    }
}
