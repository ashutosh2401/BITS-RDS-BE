package com.resume.resumeservice.dto;

import lombok.Data;
import java.util.List;

@Data
public class ResumeRequest {
    private String employeeId;
    private String name;
    private String email;
    private String phone;
    private List<String> skills;
    private List<String> experiences;
    private List<String> education;
}