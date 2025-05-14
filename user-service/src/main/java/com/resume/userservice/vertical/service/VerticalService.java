package com.resume.userservice.vertical.service;

import com.resume.userservice.vertical.response.VerticalResponse;

import java.util.List;

public interface VerticalService {
    List<VerticalResponse> getVerticalsByOrgId(String orgId);
}
