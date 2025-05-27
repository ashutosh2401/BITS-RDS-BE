package com.resume.userservice.notification.consumer;

import com.resume.userservice.config.RabbitMQConfig;
import com.resume.userservice.notification.dto.NotificationMessage;
import com.resume.userservice.notification.service.EmailSender;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @Autowired
    private EmailSender emailSender;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiveNotification(NotificationMessage message) {
        System.out.println(" [x] Received Notification:");
        System.out.println("To: " + message.getRecipientEmail());
        System.out.println("Subject: " + message.getSubject());
        System.out.println("Body: " + message.getBody());

        emailSender.sendHtmlEmail(message.getRecipientEmail(), message.getSubject(), message.getBody());
    }
}
