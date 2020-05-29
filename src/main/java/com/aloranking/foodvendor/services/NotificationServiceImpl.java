package com.aloranking.foodvendor.services;

import com.aloranking.foodvendor.models.*;
import com.aloranking.foodvendor.repositories.AuthUserRepository;
import com.aloranking.foodvendor.repositories.NotificationMessageRepository;
import com.aloranking.foodvendor.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    NotificationMessageRepository notificationMessageRepository;
    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public void sendNotification(Order order, Vendor vendor, Customer customer, Long[] menuId) {
        List<Long> menus = Arrays.asList(menuId);
        AuthUser authUser = authUserRepository.findByEmail(vendor.getEmail());
        Notification notification = new Notification();
        notification.setSubjectUser(authUser);
        notification.setOrder(order);
        notification.setMessage("customer  " + customer.getFirst_name() +" " +customer.getLast_name() + "with id" +customer.getCustomer_id()+ " ordered menu " + menus  );
        NotificationMessage message = notificationMessageRepository.findByNotificationMessage("SENT");
        notification.setNotificationMessage(message);

         notificationRepository.save(notification);

    }

    @Override
    public void sendOrderUpdateNotification(Order order, Vendor vendor, Customer customer, String message) {

        AuthUser authUser = authUserRepository.findByEmail(customer.getEmail());
        Notification notification = new Notification();
        notification.setSubjectUser(authUser);
        notification.setOrder(order);
        notification.setMessage("vendor " + vendor.getBusinessName() + " has " +message+ "  your order "   );
        NotificationMessage notificationMessage = notificationMessageRepository.findByNotificationMessage(message);
        notification.setNotificationMessage(notificationMessage);

        notificationRepository.save(notification);

    }

    @Override
    public List<Notification> getAllNotifications(AuthUser authUser) {
        return notificationRepository.findBySubjectUser(authUser);
    }


}
