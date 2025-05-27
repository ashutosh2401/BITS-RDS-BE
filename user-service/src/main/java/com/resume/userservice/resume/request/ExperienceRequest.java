package com.resume.userservice.resume.request;

import lombok.Data;

@Data
public class ExperienceRequest {
    private String company;
    private String role;
    private String from;
    private String to;
    private String location;
    private String description;
}
