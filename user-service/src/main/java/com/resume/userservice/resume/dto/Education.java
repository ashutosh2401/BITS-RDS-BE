package com.resume.userservice.resume.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Education {
    private String institution;
    private String degree;
    private String from;
    private String to;
    private String location;
}
