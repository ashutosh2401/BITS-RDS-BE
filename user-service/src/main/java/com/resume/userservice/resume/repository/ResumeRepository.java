package com.resume.userservice.resume.repository;

import com.resume.userservice.resume.entity.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResumeRepository extends MongoRepository<Resume, String> {
    List<Resume> findByEmployeeId(String employeeId);
}