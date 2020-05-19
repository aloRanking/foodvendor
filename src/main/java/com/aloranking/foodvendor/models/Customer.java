package com.aloranking.foodvendor.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customer_id;

    @NotNull
    @Column(name = "first_name")
    private String first_name;

    @NotNull
    @Column(name = "last_name")
    private String last_name;

    @Email
    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "phone_number")
    private String phone_number;

    @NotNull
    @Column(name = "amount_outstanding")
    private double amount_outstanding;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
@JsonIgnore
    private AuthUser authUser;





    public Customer() {
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public double getAmount_outstanding() {
        return amount_outstanding;
    }

    public void setAmount_outstanding(double amount_outstanding) {
        this.amount_outstanding = amount_outstanding;
    }


}
