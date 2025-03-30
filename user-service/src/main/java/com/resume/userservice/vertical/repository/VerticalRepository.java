package com.resume.userservice.vertical.repository;

import com.resume.userservice.vertical.entity.Vertical;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface VerticalRepository extends MongoRepository<Vertical, String> {
    List<Vertical> findByOrganizationId(String organizationId);
}
