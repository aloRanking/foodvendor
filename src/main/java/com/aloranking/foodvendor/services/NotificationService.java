package com.aloranking.foodvendor.services;

import com.aloranking.foodvendor.models.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface NotificationService {
    public void sendNotification(Order order, Vendor vendor, Customer customer, Long [] menuId);

    public void sendOrderUpdateNotification(Order order, Vendor vendor, Customer customer, String orderId);

    public List<Notification> getAllNotifications(AuthUser authUser);
}
