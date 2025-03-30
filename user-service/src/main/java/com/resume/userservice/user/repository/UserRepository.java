package com.resume.userservice.user.repository;

import com.resume.userservice.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    List<User> findByVerticalId(String verticalId);
    List<User> findByOrganizationId(String organizationId);
}