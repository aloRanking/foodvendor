package com.aloranking.foodvendor.services;

import com.aloranking.foodvendor.models.Customer;
import org.springframework.stereotype.Service;

@Service
public interface  CustomerService {


   public void saveCustomer(Customer customer);
   public Customer addUser(Customer customer, String password);
    public boolean isEmailAlreadyPresent(Customer customer);


    public Customer getCustomer(Long id);
}
