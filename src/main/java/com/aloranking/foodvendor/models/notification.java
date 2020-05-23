package com.aloranking.foodvendor.models;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class notification extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    String message;

    @ManyToOne
    @JoinColumn(name = "notifi_mssg_id")
    private NotificationMessage notificationMessage;



    public notification() {
    }
}
