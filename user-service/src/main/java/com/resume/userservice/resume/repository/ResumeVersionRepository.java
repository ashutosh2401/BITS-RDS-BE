package com.resume.userservice.resume.repository;

import com.resume.userservice.resume.entity.ResumeVersion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ResumeVersionRepository extends MongoRepository<ResumeVersion, String> {
    List<ResumeVersion> findByResumeId(String resumeId);

    @Query(value = "{ 'resumeId' : ?0 }", sort = "{ 'versionNumber' : -1 }")
    Optional<ResumeVersion> findTopByResumeIdOrderByVersionNumberDesc(String resumeId);
}
