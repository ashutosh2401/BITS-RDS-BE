package com.resume.userservice.user.service;

import com.resume.userservice.auth.request.RegisterRequest;
import com.resume.userservice.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();

    Optional<User> findByEmail(String email);

    void save(RegisterRequest request);
}
