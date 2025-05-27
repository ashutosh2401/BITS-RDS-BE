package com.resume.userservice.validation.service;

import com.resume.userservice.user.entity.User;
import com.resume.userservice.validation.entity.ValidationRequest;
import com.resume.userservice.validation.repository.ValidationRequestRepository;
import com.resume.userservice.validation.request.ValidationRequestCreateDto;
import com.resume.userservice.validation.response.ValidationResponse;

import java.util.List;

public interface ValidationService {
    void createValidationRequest(ValidationRequestCreateDto dto, User user);
    void acceptValidationRequest(String validationRequestId, User user);
    List<ValidationResponse> getAllValidationRequest();
    List<ValidationResponse> getAllValidationRequestForUser(User user);
}
