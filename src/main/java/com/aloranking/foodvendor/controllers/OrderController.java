package com.aloranking.foodvendor.controllers;

import com.aloranking.foodvendor.models.Customer;
import com.aloranking.foodvendor.models.Order;
import com.aloranking.foodvendor.models.Vendor;
import com.aloranking.foodvendor.repositories.CustomerRepository;
import com.aloranking.foodvendor.repositories.OrderRepository;
import com.aloranking.foodvendor.repositories.VendorRepository;
import com.aloranking.foodvendor.services.CustomerService;
import com.aloranking.foodvendor.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    @RequestMapping("home/{customerId}/create-order/{vendorId}")
    public Order createOrder(@PathVariable Long customerId, @RequestBody Order order, @PathVariable Long vendorId){
        Customer existingCustomer = customerService.getCustomer(customerId);
        Vendor existingVendor = vendorService.getVendor(vendorId);

        order.setCustomer(existingCustomer);
        order.setVendor(existingVendor);
        return orderRepository.saveAndFlush(order);

    }
}
