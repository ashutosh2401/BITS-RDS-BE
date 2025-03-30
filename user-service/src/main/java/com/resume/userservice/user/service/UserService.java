package com.resume.userservice.user.service;

import com.resume.userservice.user.dto.LoginRequest;
import com.resume.userservice.user.dto.LoginResponse;
import com.resume.userservice.user.dto.RegisterRequest;
import com.resume.userservice.user.dto.RegisterResponse;
import com.resume.userservice.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();

    Optional<User> findByEmail(String email);

    RegisterResponse save(RegisterRequest request);

    LoginResponse loginUser(LoginRequest loginRequest);
}
