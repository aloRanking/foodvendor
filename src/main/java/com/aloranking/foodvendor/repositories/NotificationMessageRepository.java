package com.aloranking.foodvendor.repositories;

import com.aloranking.foodvendor.models.NotificationMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationMessageRepository extends JpaRepository<NotificationMessage, Long> {

    public NotificationMessage findByNotificationMessage(String message);
}
