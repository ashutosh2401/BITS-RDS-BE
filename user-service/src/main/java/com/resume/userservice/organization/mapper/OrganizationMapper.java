package com.resume.userservice.organization.mapper;

import com.resume.userservice.organization.entity.Organization;
import com.resume.userservice.organization.response.OrganizationResponse;

import java.util.List;
import java.util.stream.Collectors;

public class OrganizationMapper {
    public static List<OrganizationResponse> toOrganizationResponseList(List<Organization> organizations) {
        return organizations.stream().map(organization -> {
            return OrganizationResponse.builder()
                    .orgId(organization.getOrgId())
                    .name(organization.getName())
                    .build();
        }).collect(Collectors.toList());
    }
}
