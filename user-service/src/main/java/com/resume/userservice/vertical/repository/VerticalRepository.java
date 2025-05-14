package com.resume.userservice.vertical.repository;

import com.resume.userservice.organization.entity.Organization;
import com.resume.userservice.vertical.entity.Vertical;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VerticalRepository extends MongoRepository<Vertical, String> {
    List<Vertical> findByOrganization(Organization organization);

    Optional<Vertical> findByVerticalId(String verticalId);
}
