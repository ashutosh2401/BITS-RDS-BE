package com.resume.resumeservice.controller;

import com.resume.resumeservice.dto.ResumeRequest;
import com.resume.resumeservice.entity.Resume;
import com.resume.resumeservice.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/create")
    public ResponseEntity<Resume> createResume(@RequestBody ResumeRequest request) {
        return ResponseEntity.ok(resumeService.createResume(request));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Resume>> getResumesByEmployeeId(@PathVariable String employeeId) {
        return ResponseEntity.ok(resumeService.getResumesByEmployeeId(employeeId));
    }
}