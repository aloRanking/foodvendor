package com.aloranking.foodvendor.repositories;

import com.aloranking.foodvendor.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
