package com.resume.resumeservice.service;

import com.resume.resumeservice.entity.Resume;
import com.resume.resumeservice.entity.ResumeVersion;
import com.resume.resumeservice.request.ResumeRequest;
import com.resume.resumeservice.request.ResumeVersionRequest;

import java.util.List;

public interface ResumeService {
    Resume createResume(ResumeRequest dto);
    Resume addResumeVersion(String resumeId, ResumeVersionRequest dto);
    List<ResumeVersion> getAllVersions(String resumeId);
    ResumeVersion getVersionById(String resumeId, String versionId);
    Resume activateVersion(String resumeId, String versionId);
}

