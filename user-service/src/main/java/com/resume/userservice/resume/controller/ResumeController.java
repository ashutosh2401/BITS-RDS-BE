package com.resume.userservice.resume.controller;


import com.resume.userservice.resume.entity.Resume;
import com.resume.userservice.resume.entity.ResumeVersion;
import com.resume.userservice.resume.request.ResumeRequest;
import com.resume.userservice.resume.request.ResumeVersionRequest;
import com.resume.userservice.resume.response.ResumeResponse;
import com.resume.userservice.resume.response.VersionCreateResponse;
import com.resume.userservice.resume.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    // Allow all authenticated users to create resume
    @PostMapping
    public ResponseEntity<ResumeResponse> createResume(@RequestBody ResumeRequest request,
                                                       @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        return ResponseEntity.ok(resumeService.createResume(request, email));
    }

    @GetMapping
    public ResponseEntity<?> getAllResume() {
        return ResponseEntity.ok(resumeService.getAllResume());
    }

    // Allow all authenticated users to add a version
    @PostMapping("/{resumeId}/versions")
    public ResponseEntity<VersionCreateResponse> addVersion(@PathVariable String resumeId,
                                                            @RequestBody ResumeVersionRequest resumeVersionRequest,
                                                            @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        return ResponseEntity.ok(resumeService.addResumeVersion(resumeId, resumeVersionRequest, email));
    }

    // Only ADMIN can view all versions
    @GetMapping("/{resumeId}/versions")
    public ResponseEntity<List<ResumeVersion>> getAllVersions(@PathVariable String resumeId) {
        return ResponseEntity.ok(resumeService.getAllVersions(resumeId));
    }

    // Allow all authenticated users to get a specific version
    @GetMapping("/{resumeId}/versions/{versionId}")
    public ResponseEntity<ResumeResponse> getVersion(@PathVariable String resumeId,
                                                    @PathVariable String versionId,
                                                    @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(resumeService.getVersionById(resumeId, versionId));
    }

    // Only ADMIN can activate a version
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{resumeId}/versions/{versionId}/activate")
    public ResponseEntity<?> activateVersion(@PathVariable String resumeId,
                                                  @PathVariable String versionId) {
        resumeService.activateVersion(resumeId, versionId);
        return ResponseEntity.ok("Version activated");
    }
}