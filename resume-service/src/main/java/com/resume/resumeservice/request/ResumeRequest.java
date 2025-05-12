package com.resume.resumeservice.request;

import lombok.Data;
import java.util.List;

@Data
public class ResumeRequest {
    private String employeeId;
    private String companyId;
    private String verticalId;
    private String title;
}
