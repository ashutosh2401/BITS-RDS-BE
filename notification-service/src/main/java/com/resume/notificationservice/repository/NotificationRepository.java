package com.resume.notificationservice.repository;

import com.resume.notificationservice.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByEmployeeId(String employeeId);
}