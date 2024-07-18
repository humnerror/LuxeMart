package com.luxemart.repository;

import com.luxemart.notification.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification,Long> {
}
