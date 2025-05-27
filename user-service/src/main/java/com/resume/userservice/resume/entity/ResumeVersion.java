package com.resume.userservice.resume.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "resume_versions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeVersion {
    @Id
    private String id;
    private String resumeId;
    private int versionNumber;
    private boolean isDraft;
    private boolean primary;
    private String name;
    private String email;
    private String phone;

    // Standard sections
    private List<String> skills;
    private String experiences;
    private String education;

    // Dynamic sections: Key = Section Name, Value = List of Entries
    private Map<String, List<String>> customSections;
}
