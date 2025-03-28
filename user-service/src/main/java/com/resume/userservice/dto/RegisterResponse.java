package com.resume.userservice.dto;

import lombok.Data;

@Data
public class RegisterResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String employeeId;
    private String role;
}
