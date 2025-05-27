package com.resume.userservice.validation.controller;

import com.resume.userservice.user.entity.User;
import com.resume.userservice.user.repository.UserRepository;
import com.resume.userservice.validation.entity.ValidationRequest;
import com.resume.userservice.validation.request.ValidationRequestCreateDto;
import com.resume.userservice.validation.response.ValidationResponse;
import com.resume.userservice.validation.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/validation")
public class ValidationController {
    @Autowired
    private ValidationService validationService;
    @Autowired
    private UserRepository userRepository;
    @PostMapping
    public ResponseEntity<?> createValidationRequest(@RequestBody ValidationRequestCreateDto requestDto,
                                                     @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                        .orElseThrow(() -> new RuntimeException("Validation Request not created now"));
        System.out.println(userDetails.getUsername());
        validationService.createValidationRequest(requestDto, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<List<ValidationResponse>> getValidationRequests(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Validation Request not created now"));
        System.out.println(userDetails.getUsername());
        return ResponseEntity.ok(validationService.getAllValidationRequestForUser(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{validationId}")
    public ResponseEntity<?> acceptValidationRequest(@AuthenticationPrincipal UserDetails userDetails,
                                                     @PathVariable String validationId) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Validation Request not created now"));
        System.out.println(userDetails.getUsername());
        validationService.acceptValidationRequest(validationId, user);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ValidationResponse>> getAllVadidationRequests() {
        return ResponseEntity.ok(validationService.getAllValidationRequest());
    }
}
