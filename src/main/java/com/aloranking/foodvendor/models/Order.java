package com.aloranking.foodvendor.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String order_status;
    private double amount_paid;
    private double amount_due;


    @ManyToMany
    @JoinTable(name = "ordered_menu",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "menu_id") })
    private Set<Menu> items_ordered;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public Order() {
    }

    public Set<Menu> getItems_ordered() {
        return items_ordered;
    }

    public void setItems_ordered(Set<Menu> items_ordered) {
        this.items_ordered = items_ordered;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public double getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(double amount_paid) {
        this.amount_paid = amount_paid;
    }

    public double getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(double amount_due) {
        this.amount_due = amount_due;
    }
}
