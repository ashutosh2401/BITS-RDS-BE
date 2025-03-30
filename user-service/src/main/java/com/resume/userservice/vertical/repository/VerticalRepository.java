package com.resume.userservice.vertical.repository;

import com.resume.userservice.vertical.entity.Vertical;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerticalRepository extends MongoRepository<Vertical, String> {
    List<Vertical> findByOrganizationId(String organizationId);
}
