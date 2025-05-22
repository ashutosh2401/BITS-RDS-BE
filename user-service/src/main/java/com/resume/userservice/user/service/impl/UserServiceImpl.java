package com.resume.userservice.user.service.impl;

import com.resume.userservice.auth.request.RegisterRequest;
import com.resume.userservice.organization.entity.Organization;
import com.resume.userservice.organization.repository.OrganizationRepository;
import com.resume.userservice.user.entity.User;
import com.resume.userservice.user.entity.UserRole;
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
        user.setRole(UserRole.USER);

        return userRepository.save(user);
    }
}
