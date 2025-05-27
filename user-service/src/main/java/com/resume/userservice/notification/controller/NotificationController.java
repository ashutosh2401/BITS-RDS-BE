package com.resume.userservice.notification.controller;

import com.resume.userservice.notification.dto.NotificationMessage;
import com.resume.userservice.notification.producer.NotificationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {

    @Autowired
    private NotificationProducer producer;

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationMessage message) {
        producer.sendNotification(message);
        return ResponseEntity.ok("Notification sent.");
    }
}