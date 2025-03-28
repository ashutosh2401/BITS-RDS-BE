package com.resume.resumeservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "resumes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resume {
    @Id
    private String id;
    private String employeeId;
    private String name;
    private String email;
    private String phone;
    private List<String> skills;
    private List<String> experiences;
    private List<String> education;
}