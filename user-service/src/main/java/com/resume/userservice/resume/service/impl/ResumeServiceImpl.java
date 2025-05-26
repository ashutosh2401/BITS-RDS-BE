package com.resume.userservice.resume.service.impl;

import com.resume.userservice.resume.entity.Resume;
import com.resume.userservice.resume.entity.ResumeVersion;
import com.resume.userservice.resume.mapper.ResumeMapper;
import com.resume.userservice.resume.repository.ResumeRepository;
import com.resume.userservice.resume.repository.ResumeVersionRepository;
import com.resume.userservice.resume.request.ResumeRequest;
import com.resume.userservice.resume.request.ResumeVersionRequest;
import com.resume.userservice.resume.response.ResumeResponse;
import com.resume.userservice.resume.response.VersionCreateResponse;
import com.resume.userservice.resume.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return ResumeMapper.mapToResumeResponse(savedResume, savedVersion);
    }

    @Override
    public VersionCreateResponse addResumeVersion(String resumeId, ResumeVersionRequest dto, String email) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        Optional<ResumeVersion> latestVersion = resumeVersionRepository.findTopByResumeIdOrderByVersionNumberDesc(resumeId);
        int versionNumber = latestVersion.map(v -> v.getVersionNumber() + 1).orElse(1);
        System.out.println("add version " + resume.getId() + " version no " + versionNumber);
        ResumeVersion version = ResumeVersion.builder()
                .versionNumber(versionNumber)
                .isDraft(dto.isDraft())
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .skills(dto.getSkills())
                .experiences(dto.getExperiences())
                .education(dto.getEducation())
                .customSections(dto.getCustomSections())
                .build();
        ResumeVersion savedVersion = resumeVersionRepository.save(version);
        return new VersionCreateResponse(savedVersion.getId());
    }

    @Override
    public List<Resume> getAllResume() {
        return resumeRepository.findAll();
    }

    @Override
    public List<ResumeVersion> getAllVersions(String resumeId) {
        return resumeVersionRepository.findByResumeId(resumeId);
    }

    @Override
    public ResumeResponse getVersionById(String resumeId, String versionId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume not found."));
        ResumeVersion resumeVersion = resumeVersionRepository.findById(versionId)
                .orElseThrow(() -> new RuntimeException("Resume version not found."));
        return ResumeMapper.mapToResumeResponse(resume, resumeVersion);
    }

    @Override
    public void activateVersion(String resumeId, String versionId) {
        ResumeVersion resumeVersion = resumeVersionRepository.findById(versionId)
                .orElseThrow(() -> new RuntimeException("Resume version not found."));
        resumeVersion.setPrimary(true);
        resumeVersionRepository.save(resumeVersion);
    }
}