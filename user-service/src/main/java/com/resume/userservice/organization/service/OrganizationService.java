package com.resume.userservice.organization.service;

import com.resume.userservice.organization.response.OrganizationResponse;

import java.util.List;

public interface OrganizationService {
    List<OrganizationResponse> getAllOrganizations();
}
