package com.resume.userservice.resume.service;

import com.resume.userservice.resume.entity.Resume;
import com.resume.userservice.resume.entity.ResumeVersion;
import com.resume.userservice.resume.request.ResumeRequest;
import com.resume.userservice.resume.request.ResumeVersionRequest;

import java.util.List;

public interface ResumeService {
    Resume createResume(ResumeRequest dto, String email);
    Resume addResumeVersion(String resumeId, ResumeVersionRequest dto, String email);
    List<Resume> getAllResume();
    List<ResumeVersion> getAllVersions(String resumeId);
    ResumeVersion getVersionById(String resumeId, String versionId);
    Resume activateVersion(String resumeId, String versionId);
}

