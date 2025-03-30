package com.resume.userservice.organization.controller;

import com.resume.userservice.organization.dto.OrgRegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {
    @PostMapping("/register")
    public static String registerOrganization(@RequestBody OrgRegisterRequest request) {
        return "";
    }
}
