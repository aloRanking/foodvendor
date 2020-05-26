package com.aloranking.foodvendor.services;

import com.aloranking.foodvendor.models.Customer;
import com.aloranking.foodvendor.models.Order;
import com.aloranking.foodvendor.models.Vendor;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    public void sendNotification(Order order, Vendor vendor, Customer customer, Long [] menuId);

    public void sendOrderUpdateNotification(Order order, Vendor vendor, Customer customer, String orderId);
}
