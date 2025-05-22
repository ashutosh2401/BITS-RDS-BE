package com.resume.userservice.notification.producer;

import com.resume.userservice.config.RabbitMQConfig;
import com.resume.userservice.notification.dto.NotificationMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public NotificationProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendNotification(NotificationMessage message) {
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, message);
        System.out.println(" [x] Sent notification to: " + message.getRecipientEmail());
    }
}
