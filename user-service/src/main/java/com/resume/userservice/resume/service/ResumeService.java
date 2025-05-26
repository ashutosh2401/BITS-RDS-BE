package com.resume.userservice.resume.service;

import com.resume.userservice.resume.entity.Resume;
import com.resume.userservice.resume.entity.ResumeVersion;
import com.resume.userservice.resume.request.ResumeRequest;
import com.resume.userservice.resume.request.ResumeVersionRequest;
import com.resume.userservice.resume.response.ResumeResponse;
import com.resume.userservice.resume.response.VersionCreateResponse;

import java.util.List;

public interface ResumeService {
    ResumeResponse createResume(ResumeRequest dto, String email);
    VersionCreateResponse addResumeVersion(String resumeId, ResumeVersionRequest dto, String email);
    List<Resume> getAllResume();
    List<ResumeVersion> getAllVersions(String resumeId);
    ResumeResponse getVersionById(String resumeId, String versionId);
    void activateVersion(String resumeId, String versionId);
}

