package com.resume.userservice.validation.repository;

import com.resume.userservice.validation.entity.ValidationRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValidationRequestRepository extends MongoRepository<ValidationRequest, String> {
    List<ValidationRequest> findAllByRequesterUserId(String requesterUserId);

    List<ValidationRequest> findAllByAccepted(boolean accepted);
}
