package com.resume.userservice.auth.mapper;

import com.resume.userservice.auth.response.AuthUserResponse;
import com.resume.userservice.user.entity.User;

public class AuthUserMapper {
    public static AuthUserResponse toAuthUserResponse(User user) {
        return AuthUserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .employeeId(user.getEmployeeId())
                .email(user.getEmail())
                .companyId(user.getOrganization().getOrgId())
                .build();
    }
}
