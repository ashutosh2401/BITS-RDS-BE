package com.resume.userservice.validation.mapper;

import com.resume.userservice.validation.entity.ValidationRequest;
import com.resume.userservice.validation.response.ValidationResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationRequestMapper {
    public static List<ValidationResponse> toValidationResponses(List<ValidationRequest> validationRequests) {
        return validationRequests.stream()
                .map(validationRequest -> {
                    return ValidationResponse.builder()
                            .id(validationRequest.getId())
                            .versionId(validationRequest.getVersionId())
                            .resumeId(validationRequest.getResumeId())
                            .accepted(validationRequest.isAccepted())
                            .acceptorUserId(validationRequest.getAcceptorUserId())
                            .requesterUserId(validationRequest.getRequesterUserId())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
