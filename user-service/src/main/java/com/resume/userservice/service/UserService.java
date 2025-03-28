package com.resume.userservice.service;

import com.resume.userservice.dto.LoginRequest;
import com.resume.userservice.dto.LoginResponse;
import com.resume.userservice.dto.RegisterRequest;
import com.resume.userservice.dto.RegisterResponse;
import com.resume.userservice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();

    Optional<User> findByEmail(String email);

    RegisterResponse save(RegisterRequest request);

    LoginResponse loginUser(LoginRequest loginRequest);
}
