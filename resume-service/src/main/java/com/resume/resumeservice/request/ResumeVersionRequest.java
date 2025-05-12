package com.resume.resumeservice.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ResumeVersionRequest {
    private boolean isDraft;
    private String name;
    private String email;
    private String phone;
    private List<String> skills;
    private List<String> experiences;
    private List<String> education;
    private Map<String, List<String>> customSections;
}
