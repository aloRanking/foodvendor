package com.aloranking.foodvendor.models;

import javax.persistence.*;

@Entity
@Table(name = "notification_message")
public class notificationMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String notification_message;

    public notificationMessage() {
    }
}
