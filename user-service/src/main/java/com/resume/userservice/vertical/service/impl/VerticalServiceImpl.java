package com.resume.userservice.vertical.service.impl;

import com.resume.userservice.organization.entity.Organization;
import com.resume.userservice.organization.repository.OrganizationRepository;
import com.resume.userservice.vertical.entity.Vertical;
import com.resume.userservice.vertical.mapper.VerticalMapper;
import com.resume.userservice.vertical.repository.VerticalRepository;
import com.resume.userservice.vertical.response.VerticalResponse;
import com.resume.userservice.vertical.service.VerticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerticalServiceImpl implements VerticalService {
    @Autowired
    private VerticalRepository verticalRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public List<VerticalResponse> getVerticalsByOrgId(String orgId) {
        Organization org = organizationRepository.findByOrgId(orgId)
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        List<Vertical> verticals = verticalRepository.findByOrganization(org);
        return VerticalMapper.toVerticalResponseList(verticals);
    }
}
