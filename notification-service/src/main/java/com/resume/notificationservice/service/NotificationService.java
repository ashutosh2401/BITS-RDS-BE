package com.resume.notificationservice.service;

import com.resume.notificationservice.dto.NotificationRequest;
import com.resume.notificationservice.entity.Notification;
import com.resume.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;

    public Notification sendNotification(NotificationRequest request) {
        Notification notification = Notification.builder()
                .employeeId(request.getEmployeeId())
                .email(request.getEmail())
                .message(request.getMessage())
                .sent(false)
                .build();

        try {
            sendEmail(request.getEmail(), "Resume Shared", request.getMessage());
            notification.setSent(true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return notificationRepository.save(notification);
    }

    private void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);
        mailSender.send(message);
    }
}