package com.resume.userservice.resume.request;

import lombok.Data;

@Data
public class ResumeRequest {
    private String employeeId;
    private String companyId;
    private String verticalId;
    private String title;
}
