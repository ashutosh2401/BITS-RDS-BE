package com.resume.userservice.vertical.controller;

import com.resume.userservice.vertical.response.VerticalResponse;
import com.resume.userservice.vertical.service.VerticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/verticals")
public class VerticalController {
    @Autowired
    private VerticalService verticalService;

    @GetMapping("/by-org/{orgId}")
    public ResponseEntity<List<VerticalResponse>> getVerticalsByOrgId(@PathVariable String orgId) {
        return ResponseEntity.ok(verticalService.getVerticalsByOrgId(orgId));
    }
}
