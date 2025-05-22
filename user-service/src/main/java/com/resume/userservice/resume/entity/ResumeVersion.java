package com.resume.userservice.resume.entity;

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
public class ResumeVersion {
    private String versionId;
    private int versionNumber;
    private boolean isDraft;
    private String name;
    private String email;
    private String phone;

    // Standard sections
    private List<String> skills;
    private List<String> experiences;
    private List<String> education;

    // Dynamic sections: Key = Section Name, Value = List of Entries
    private Map<String, List<String>> customSections;
}
