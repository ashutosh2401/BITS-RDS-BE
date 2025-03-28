package com.resume.notificationservice.config;

import com.resume.notificationservice.dto.NotificationRequest;
import com.resume.notificationservice.event.ResumeSharedEvent;
import com.resume.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    private final NotificationService notificationService;

    @KafkaListener(topics = "resume-shared-topic", groupId = "notification-group")
    public void consume(ResumeSharedEvent event) {
        notificationService.sendNotification(
                new NotificationRequest(event.getEmployeeId(), event.getEmail(), event.getMessage())
        );
    }
}