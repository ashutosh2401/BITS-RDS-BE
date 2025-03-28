package com.resume.resumeservice.service;

import com.resume.resumeservice.dto.ResumeRequest;
import com.resume.resumeservice.entity.Resume;
import com.resume.resumeservice.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;

    public Resume createResume(ResumeRequest request) {
        Resume resume = Resume.builder()
                .employeeId(request.getEmployeeId())
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .skills(request.getSkills())
                .experiences(request.getExperiences())
                .education(request.getEducation())
                .build();
        return resumeRepository.save(resume);
    }

    public List<Resume> getResumesByEmployeeId(String employeeId) {
        return resumeRepository.findByEmployeeId(employeeId);
    }
}