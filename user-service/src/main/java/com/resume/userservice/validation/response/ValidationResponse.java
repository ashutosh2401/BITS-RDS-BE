package com.resume.userservice.validation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationResponse {
    private String id;
    private String resumeId;
    private String versionId;
    private boolean accepted;
    private String requesterUserId;
    private String acceptorUserId;
}
