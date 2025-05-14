package com.resume.userservice.organization.service.impl;

import com.resume.userservice.organization.entity.Organization;
import com.resume.userservice.organization.mapper.OrganizationMapper;
import com.resume.userservice.organization.repository.OrganizationRepository;
import com.resume.userservice.organization.response.OrganizationResponse;
import com.resume.userservice.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public List<OrganizationResponse> getAllOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();
        return OrganizationMapper.toOrganizationResponseList(organizations);
    }
}
