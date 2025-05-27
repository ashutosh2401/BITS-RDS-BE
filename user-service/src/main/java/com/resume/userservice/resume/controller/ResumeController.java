package com.resume.userservice.resume.controller;

import com.resume.userservice.resume.entity.ResumeVersion;
import com.resume.userservice.resume.request.ResumeRequest;
import com.resume.userservice.resume.request.ResumeVersionRequest;
import com.resume.userservice.resume.response.ResumeResponse;
import com.resume.userservice.resume.response.ResumeVersionResponse;
import com.resume.userservice.resume.response.VersionCreateResponse;
import com.resume.userservice.resume.response.VersionUpdateResponse;
import com.resume.userservice.resume.service.ResumeService;
import com.resume.userservice.user.entity.User;
import com.resume.userservice.user.service.UserService;
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

    @Autowired
    private UserService userService;

    // Allow all authenticated users to create resume
    @PostMapping
    public ResponseEntity<ResumeResponse> createResume(@RequestBody ResumeRequest request,
                                                       @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        return ResponseEntity.ok(resumeService.createResume(request, email));
    }

    @GetMapping
    public ResponseEntity<?> getAllResume(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).build();
        }
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email).orElse(null);
        return ResponseEntity.ok(resumeService.getAllResume(user));
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<ResumeResponse> getResumeById(@PathVariable String resumeId) {
        return ResponseEntity.ok(resumeService.getResumeById(resumeId));
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
    public ResponseEntity<ResumeVersionResponse> getVersion(@PathVariable String resumeId,
                                                            @PathVariable String versionId,
                                                            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(resumeService.getVersionById(versionId));
    }

    @PutMapping("/{resumeId}/versions/{versionId}")
    public ResponseEntity<VersionUpdateResponse> updateVersion(@PathVariable String resumeId,
                                                     @PathVariable String versionId,
                                                     @RequestBody ResumeVersionRequest versionRequest,
                                                     @AuthenticationPrincipal UserDetails userDetails) {
        try {
            resumeService.updateResumeVersion(versionId, versionRequest);
            return ResponseEntity.ok(new VersionUpdateResponse("Version saved Successfully"));
        } catch (Exception e) {
            System.out.println("Error occurred while saving resume version");
        }
        return ResponseEntity.badRequest()
                .body(new VersionUpdateResponse("Error occurred while saving resume version"));
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