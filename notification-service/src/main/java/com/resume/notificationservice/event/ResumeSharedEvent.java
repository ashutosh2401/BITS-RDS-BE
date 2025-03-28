package com.resume.notificationservice.event;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeSharedEvent {
    private String employeeId;
    private String email;
    private String message;
}