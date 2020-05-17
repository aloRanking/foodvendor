package com.aloranking.foodvendor.models;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public notification() {
    }
}
