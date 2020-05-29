package com.aloranking.foodvendor.repositories;

import com.aloranking.foodvendor.models.AuthUser;
import com.aloranking.foodvendor.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    public List<Notification> findBySubjectUser(AuthUser authUser);
}
