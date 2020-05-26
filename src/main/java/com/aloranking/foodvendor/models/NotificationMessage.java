package com.aloranking.foodvendor.models;

import javax.persistence.*;

@Entity
@Table(name = "notification_message")
public class NotificationMessage  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String notificationMessage;

    public NotificationMessage() {
    }

    public NotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }
}
