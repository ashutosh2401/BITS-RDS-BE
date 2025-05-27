package com.resume.userservice.resume.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Experience {
    private String company;
    private String role;
    private String from;
    private String to;
    private String location;
    private String description;
}
