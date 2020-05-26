package com.aloranking.foodvendor.models;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "auth_user")
    private AuthUser subjectUser;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    String message;

    @ManyToOne
    @JoinColumn(name = "notifi_mssg_id")
    private NotificationMessage notificationMessage;



    public Notification() {
    }

    public AuthUser getSubjectUser() {
        return subjectUser;
    }

    public void setSubjectUser(AuthUser subjectUser) {
        this.subjectUser = subjectUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationMessage getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(NotificationMessage notificationMessage) {
        this.notificationMessage = notificationMessage;
    }
}
