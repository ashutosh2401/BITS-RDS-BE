package com.resume.resumeservice.repository;

import com.resume.resumeservice.entity.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ResumeRepository extends MongoRepository<Resume, String> {
    List<Resume> findByEmployeeId(String employeeId);
}