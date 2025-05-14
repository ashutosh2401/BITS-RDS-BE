package com.resume.userservice.organization.controller;

import com.resume.userservice.organization.response.OrganizationResponse;
import com.resume.userservice.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    @GetMapping()
    public ResponseEntity<List<OrganizationResponse>> getAllOrganizations() {
        return ResponseEntity.ok(organizationService.getAllOrganizations());
    }
}
