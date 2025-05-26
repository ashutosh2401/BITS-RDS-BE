package com.resume.userservice.resume.mapper;

import com.resume.userservice.resume.entity.Resume;
import com.resume.userservice.resume.entity.ResumeVersion;
import com.resume.userservice.resume.response.ResumeResponse;
import com.resume.userservice.resume.response.ResumeVersionResponse;

public class ResumeMapper {
    public static ResumeResponse mapToResumeResponse(Resume resume, ResumeVersion primaryVersion) {
        return ResumeResponse.builder()
                .id(resume.getId())
                .employeeId(resume.getEmployeeId())
                .companyId(resume.getCompanyId())
                .verticalId(resume.getVerticalId())
                .title(resume.getTitle())
                .primaryVersion(mapToResumeVersionResponse(primaryVersion))
                .build();
    }

    public static ResumeVersionResponse mapToResumeVersionResponse(ResumeVersion version) {
        return ResumeVersionResponse.builder()
                .id(version.getId())
                .versionNumber(version.getVersionNumber())
                .isDraft(version.isDraft())
                .primary(version.isPrimary())
                .name(version.getName())
                .email(version.getEmail())
                .phone(version.getPhone())
                .skills(version.getSkills())
                .experiences(version.getExperiences())
                .education(version.getEducation())
                .customSections(version.getCustomSections())
                .build();
    }
}
