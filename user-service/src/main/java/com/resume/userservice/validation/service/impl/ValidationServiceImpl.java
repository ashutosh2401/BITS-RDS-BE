package com.resume.userservice.validation.service.impl;

import com.resume.userservice.user.entity.User;
import com.resume.userservice.validation.entity.ValidationRequest;
import com.resume.userservice.validation.mapper.ValidationRequestMapper;
import com.resume.userservice.validation.repository.ValidationRequestRepository;
import com.resume.userservice.validation.request.ValidationRequestCreateDto;
import com.resume.userservice.validation.response.ValidationResponse;
import com.resume.userservice.validation.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private ValidationRequestRepository validationRequestRepository;

    @Override
    public void createValidationRequest(ValidationRequestCreateDto dto, User user) {
        ValidationRequest validationRequest =
                ValidationRequest.builder()
                        .resumeId(dto.getResumeId())
                        .versionId(dto.getVersionId())
                        .accepted(false)
                        .requesterUserId(user.getId())
                        .acceptorUserId(null)
                        .build();
        validationRequestRepository.save(validationRequest);
    }

    @Override
    public void acceptValidationRequest(String validationRequestId, User user) {
        ValidationRequest validationRequest = validationRequestRepository.findById(validationRequestId)
                .orElseThrow(() -> new RuntimeException("Validation Request not found"));
        validationRequest.setAcceptorUserId(user.getId());
        validationRequest.setAccepted(true);
        validationRequestRepository.save(validationRequest);
    }

    @Override
    public List<ValidationResponse> getAllValidationRequest() {
        List<ValidationRequest> validationRequestList = validationRequestRepository.findAllByAccepted(false);
        return ValidationRequestMapper.toValidationResponses(validationRequestList);
    }

    @Override
    public List<ValidationResponse> getAllValidationRequestForUser(User user) {
        List<ValidationRequest> validationRequestList = validationRequestRepository.findAllByRequesterUserId(user.getId());
        return ValidationRequestMapper.toValidationResponses(validationRequestList);
    }
}
