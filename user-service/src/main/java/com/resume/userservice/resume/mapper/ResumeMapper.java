package com.resume.userservice.resume.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resume.userservice.resume.dto.Education;
import com.resume.userservice.resume.dto.Experience;
import com.resume.userservice.resume.entity.Resume;
import com.resume.userservice.resume.entity.ResumeVersion;
import com.resume.userservice.resume.request.ExperienceRequest;
import com.resume.userservice.resume.response.ResumeResponse;
import com.resume.userservice.resume.response.ResumeVersionResponse;

import java.util.List;

public class ResumeMapper {
    public static ResumeResponse mapToResumeResponse(Resume resume) {
        return ResumeResponse.builder()
                .id(resume.getId())
                .employeeId(resume.getEmployeeId())
                .companyId(resume.getCompanyId())
                .verticalId(resume.getVerticalId())
                .title(resume.getTitle())
                .build();
    }

    public static ResumeVersionResponse mapToResumeVersionResponse(ResumeVersion version) {
        ObjectMapper mapper = new ObjectMapper();

        List<Experience> experiencesList;
        List<Education> educationList;

        try {
            experiencesList = mapper.readValue(version.getExperiences(), new TypeReference<List<Experience>>() {});
            educationList = mapper.readValue(version.getEducation(), new TypeReference<List<Education>>() {});
        } catch (Exception e) {
            experiencesList = List.of();
            educationList = List.of();
        }

        return ResumeVersionResponse.builder()
                .id(version.getId())
                .versionNumber(version.getVersionNumber())
                .isDraft(version.isDraft())
                .primary(version.isPrimary())
                .name(version.getName())
                .email(version.getEmail())
                .phone(version.getPhone())
                .skills(version.getSkills())
                .experiences(experiencesList)
                .education(educationList)
                .customSections(version.getCustomSections())
                .build();
    }
}
