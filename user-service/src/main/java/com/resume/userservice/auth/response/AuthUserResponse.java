package com.resume.userservice.auth.response;

import com.resume.userservice.user.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserResponse {
    private String id;
    private String employeeId;
    private String companyId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole role;
}
