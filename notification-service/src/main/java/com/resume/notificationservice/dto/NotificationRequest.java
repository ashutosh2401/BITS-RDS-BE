package com.resume.notificationservice.dto;

import lombok.Data;

@Data
public class NotificationRequest {
    private String employeeId;
    private String email;
    private String message;
}