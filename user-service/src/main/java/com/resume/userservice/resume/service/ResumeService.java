package com.resume.userservice.resume.service;

import com.resume.userservice.resume.entity.Resume;
import com.resume.userservice.resume.entity.ResumeVersion;
import com.resume.userservice.resume.request.ResumeRequest;
import com.resume.userservice.resume.request.ResumeVersionRequest;
import com.resume.userservice.resume.response.ResumeResponse;
import com.resume.userservice.resume.response.ResumeVersionResponse;
import com.resume.userservice.resume.response.VersionCreateResponse;
import com.resume.userservice.user.entity.User;
import com.resume.userservice.vertical.response.VerticalResponse;

import java.util.List;

public interface ResumeService {
    ResumeResponse createResume(ResumeRequest dto, String email);
    VersionCreateResponse addResumeVersion(String resumeId, ResumeVersionRequest dto, String email);
    void updateResumeVersion(String versionId, ResumeVersionRequest dto);
    List<Resume> getAllResume(User user);
    ResumeResponse getResumeById(String resumeId);
    List<ResumeVersion> getAllVersions(String resumeId);
    ResumeVersionResponse getVersionById(String versionId);
    void activateVersion(String resumeId, String versionId);
}

