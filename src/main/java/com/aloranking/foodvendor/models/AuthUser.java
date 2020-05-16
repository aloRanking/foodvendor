package com.aloranking.foodvendor.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "auth_users")
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Email
    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    private String password;

    @OneToOne
    @JoinColumn(name = "customer_user_id", nullable = true)
    private Customer customer;


    @OneToOne
    @JoinColumn(name = "vendor_user_id", nullable = true)
    private Vendor vendor;

    public AuthUser() {
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
