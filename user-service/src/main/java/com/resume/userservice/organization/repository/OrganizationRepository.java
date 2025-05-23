package com.resume.userservice.organization.repository;

import com.resume.userservice.organization.entity.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends MongoRepository<Organization, String> {
}
