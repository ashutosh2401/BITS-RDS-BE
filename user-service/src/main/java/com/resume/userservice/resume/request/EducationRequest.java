package com.resume.userservice.resume.request;

import lombok.Data;

@Data
public class EducationRequest {
    private String institution;
    private String degree;
    private String from;
    private String to;
    private String location;
}
