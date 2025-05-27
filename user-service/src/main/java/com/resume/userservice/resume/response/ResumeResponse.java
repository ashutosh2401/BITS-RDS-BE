package com.resume.userservice.resume.response;

import com.resume.userservice.resume.entity.ResumeVersion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeResponse {
    private String id;
    private String employeeId;
    private String companyId;
    private String verticalId;
    private String title;
}