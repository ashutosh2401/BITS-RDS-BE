package com.resume.userservice.notification.consumer;

import com.resume.userservice.config.RabbitMQConfig;
import com.resume.userservice.notification.dto.NotificationMessage;
import com.resume.userservice.notification.service.EmailSender;
import com.resume.userservice.resume.response.ResumeVersionResponse;
import com.resume.userservice.resume.service.ResumeService;
import com.resume.userservice.util.EmailUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private ResumeService resumeService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiveNotification(NotificationMessage message) {
        System.out.println(" [x] Received Notification:");
        System.out.println("To: " + message.getRecipientEmail());
        System.out.println("Subject: " + message.getSubject());
        System.out.println("Body: " + message.getBody());

        ResumeVersionResponse versionResponse = resumeService.getVersionById(message.getVersionId());
        String htmlBody = EmailUtil.buildEmailBody(message.getBody(), versionResponse);
        emailSender.sendHtmlEmail(message.getRecipientEmail(), message.getSubject(), htmlBody);
    }
}
