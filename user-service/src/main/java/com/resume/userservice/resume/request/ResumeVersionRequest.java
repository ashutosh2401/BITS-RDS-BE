package com.resume.userservice.resume.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ResumeVersionRequest {
    private boolean draft;
    private String name;
    private String email;
    private String phone;
    private List<String> skills;
    private List<ExperienceRequest> experiences;
    private List<EducationRequest> education;
    private Map<String, List<String>> customSections;
}
