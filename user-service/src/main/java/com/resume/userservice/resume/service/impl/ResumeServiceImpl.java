package com.resume.userservice.resume.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resume.userservice.resume.entity.Resume;
import com.resume.userservice.resume.entity.ResumeVersion;
import com.resume.userservice.resume.mapper.ResumeMapper;
import com.resume.userservice.resume.repository.ResumeRepository;
import com.resume.userservice.resume.repository.ResumeVersionRepository;
import com.resume.userservice.resume.request.ResumeRequest;
import com.resume.userservice.resume.request.ResumeVersionRequest;
import com.resume.userservice.resume.response.ResumeResponse;
import com.resume.userservice.resume.response.ResumeVersionResponse;
import com.resume.userservice.resume.response.VersionCreateResponse;
import com.resume.userservice.resume.service.ResumeService;
import com.resume.userservice.user.entity.User;
import com.resume.userservice.vertical.response.VerticalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private ResumeVersionRepository resumeVersionRepository;

    @Override
    public ResumeResponse createResume(ResumeRequest dto, String email) {
        Resume resume = Resume.builder()
                .employeeId(dto.getEmployeeId())
                .companyId(dto.getCompanyId())
                .verticalId(dto.getVerticalId())
                .title(dto.getTitle())
                .build();

        Resume savedResume = resumeRepository.save(resume);

        ResumeVersion initialVersion = ResumeVersion.builder()
                .resumeId(savedResume.getId())
                .versionNumber(1)
                .isDraft(false)
                .primary(true)
                .build();

        ResumeVersion savedVersion = resumeVersionRepository.save(initialVersion);
        savedResume.setLatestVersionId(savedVersion.getId());
        resumeRepository.save(savedResume);
        return ResumeMapper.mapToResumeResponse(savedResume);
    }

    @Override
    public VersionCreateResponse addResumeVersion(String resumeId, ResumeVersionRequest request, String userId) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Resume resume = resumeRepository.findById(resumeId)
                    .orElseThrow(() -> new RuntimeException("Resume not found."));
            String experiencesJson = mapper.writeValueAsString(request.getExperiences());
            String educationJson = mapper.writeValueAsString(request.getEducation());

            ResumeVersion resumeVersion = ResumeVersion.builder()
                    .resumeId(resumeId)
                    .name(request.getName())
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .skills(request.getSkills())
                    .experiences(experiencesJson)
                    .education(educationJson)
                    .isDraft(request.isDraft())
                    .build();

            ResumeVersion savedVersion = resumeVersionRepository.save(resumeVersion);
            resume.setLatestVersionId(savedVersion.getId());
            resumeRepository.save(resume);
            return new VersionCreateResponse(resumeVersion.getId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing resume data", e);
        }
    }

    @Override
    public void updateResumeVersion(String versionId, ResumeVersionRequest dto) {
        ResumeVersion resumeVersion = resumeVersionRepository.findById(versionId)
                .orElseThrow(() -> new RuntimeException("Resume version not found."));

        resumeVersion.setDraft(dto.isDraft());
        resumeVersion.setName(dto.getName());
        resumeVersion.setEmail(dto.getEmail());
        resumeVersion.setPhone(dto.getPhone());
        resumeVersion.setSkills(dto.getSkills());

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String educationJson = objectMapper.writeValueAsString(dto.getEducation());
            String experiencesJson = objectMapper.writeValueAsString(dto.getExperiences());

            resumeVersion.setEducation(educationJson);
            resumeVersion.setExperiences(experiencesJson);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize education or experiences", e);
        }

        resumeVersionRepository.save(resumeVersion);
    }

    @Override
    public List<Resume> getAllResume(User user) {
        return resumeRepository.findByEmployeeId(user.getEmployeeId());
    }

    @Override
    public ResumeResponse getResumeById(String resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume not found."));
        return ResumeMapper.mapToResumeResponse(resume);
    }

    @Override
    public List<ResumeVersion> getAllVersions(String resumeId) {
        return resumeVersionRepository.findByResumeId(resumeId);
    }

    @Override
    public ResumeVersionResponse getVersionById(String versionId) {
        ResumeVersion resumeVersion = resumeVersionRepository.findById(versionId)
                .orElseThrow(() -> new RuntimeException("Resume version not found."));
        return ResumeMapper.mapToResumeVersionResponse(resumeVersion);
    }

    @Override
    public void activateVersion(String resumeId, String versionId) {
        ResumeVersion resumeVersion = resumeVersionRepository.findById(versionId)
                .orElseThrow(() -> new RuntimeException("Resume version not found."));
        resumeVersion.setPrimary(true);
        resumeVersionRepository.save(resumeVersion);
    }
}