package com.resume.userservice.auth.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String employeeId;
    private String orgId;
    private String verticalId;
}