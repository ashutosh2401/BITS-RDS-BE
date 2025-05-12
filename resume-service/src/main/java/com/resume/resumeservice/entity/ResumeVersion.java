package com.resume.resumeservice.entity;

import lombok.*;
import java.util.Map;
import java.util.List;

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
