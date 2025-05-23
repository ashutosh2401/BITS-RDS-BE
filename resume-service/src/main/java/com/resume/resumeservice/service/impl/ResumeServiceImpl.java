package com.resume.resumeservice.service.impl;

import com.resume.resumeservice.entity.ResumeVersion;
import com.resume.resumeservice.request.ResumeRequest;
import com.resume.resumeservice.entity.Resume;
import com.resume.resumeservice.repository.ResumeRepository;
import com.resume.resumeservice.request.ResumeVersionRequest;
import com.resume.resumeservice.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Override
    public Resume createResume(ResumeRequest dto) {
        Resume resume = Resume.builder()
                .employeeId(dto.getEmployeeId())
                .companyId(dto.getCompanyId())
                .verticalId(dto.getVerticalId())
                .title(dto.getTitle())
                .versions(new ArrayList<>())
                .build();
        System.out.println("new resume " + resume);
        return resumeRepository.save(resume);
    }

    @Override
    public Resume addResumeVersion(String resumeId, ResumeVersionRequest dto) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        int versionNumber = resume.getVersions().size() + 1;
        String versionId = UUID.randomUUID().toString();
        System.out.println("add version " + resume.getId() + " version no " + versionNumber + " version id " + versionId);
        ResumeVersion version = ResumeVersion.builder()
                .versionId(versionId)
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
        System.out.println("resume version " + version.toString());
        resume.getVersions().add(version);
        return resumeRepository.save(resume);
    }

    @Override
    public List<ResumeVersion> getAllVersions(String resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
        return resume.getVersions();
    }

    @Override
    public ResumeVersion getVersionById(String resumeId, String versionId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
        return resume.getVersions().stream()
                .filter(v -> v.getVersionId().equals(versionId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Version not found"));
    }

    @Override
    public Resume activateVersion(String resumeId, String versionId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        for (ResumeVersion version : resume.getVersions()) {
            version.setDraft(!version.getVersionId().equals(versionId));  // deactivate others
        }

        return resumeRepository.save(resume);
    }
}