package com.resume.userservice.validation.request;

import lombok.Data;

@Data
public class ValidationRequestCreateDto {
    private String resumeId;
    private String versionId;
}
