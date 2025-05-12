package com.resume.resumeservice.controller;

import com.resume.resumeservice.entity.ResumeVersion;
import com.resume.resumeservice.request.ResumeRequest;
import com.resume.resumeservice.entity.Resume;
import com.resume.resumeservice.request.ResumeVersionRequest;
import com.resume.resumeservice.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<Resume> createResume(@RequestBody ResumeRequest request) {
        System.out.println("create resume request created");
        return ResponseEntity.ok(resumeService.createResume(request));
    }

    @PostMapping("/{resumeId}/versions")
    public ResponseEntity<Resume> addVersion(@PathVariable String resumeId,
                                             @RequestBody ResumeVersionRequest resumeVersionRequest) {
        System.out.println("create resume version request created");
        return ResponseEntity.ok(resumeService.addResumeVersion(resumeId, resumeVersionRequest));
    }

    @GetMapping("/{resumeId}/versions")
    public ResponseEntity<List<ResumeVersion>> getAllVersions(@PathVariable String resumeId) {
        return ResponseEntity.ok(resumeService.getAllVersions(resumeId));
    }

    @GetMapping("/{resumeId}/versions/{versionId}")
    public ResponseEntity<ResumeVersion> getVersion(@PathVariable String resumeId,
                                                    @PathVariable String versionId) {
        return ResponseEntity.ok(resumeService.getVersionById(resumeId, versionId));
    }

    @PutMapping("/{resumeId}/versions/{versionId}/activate")
    public ResponseEntity<Resume> activateVersion(@PathVariable String resumeId,
                                                  @PathVariable String versionId) {
        return ResponseEntity.ok(resumeService.activateVersion(resumeId, versionId));
    }
}