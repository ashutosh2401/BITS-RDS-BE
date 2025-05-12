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
    private String companyId;
    private String verticalId;
    private String title;
    private List<ResumeVersion> versions;
}
