package com.resume.userservice.resume.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeVersionResponse {
    private String id;
    private int versionNumber;
    private boolean isDraft;
    private boolean primary;
    private String name;
    private String email;
    private String phone;

    private List<String> skills;
    private List<String> experiences;
    private List<String> education;
    private Map<String, List<String>> customSections;
}
