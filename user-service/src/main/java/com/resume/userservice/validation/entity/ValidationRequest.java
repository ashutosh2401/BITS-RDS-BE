package com.resume.userservice.validation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "validation_requests")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationRequest {
    @Id
    private String id;
    private String resumeId;
    private String versionId;
    private boolean accepted;
    private String requesterUserId;
    private String acceptorUserId;
}
