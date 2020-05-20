package com.aloranking.foodvendor.controllers;

import com.aloranking.foodvendor.models.Customer;
import com.aloranking.foodvendor.models.Order;
import com.aloranking.foodvendor.models.OrderStatus;
import com.aloranking.foodvendor.models.Vendor;
import com.aloranking.foodvendor.repositories.CustomerRepository;
import com.aloranking.foodvendor.repositories.OrderRepository;
import com.aloranking.foodvendor.repositories.OrderStatusRepository;
import com.aloranking.foodvendor.repositories.VendorRepository;
import com.aloranking.foodvendor.services.CustomerService;
import com.aloranking.foodvendor.services.OrderService;
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

    @Autowired
    private OrderService orderService;

    @PostMapping
    @RequestMapping("home/{customerId}/create-order/{vendorId}")
    public String createOrder(@PathVariable Long customerId,
                              @RequestBody Order order, @PathVariable Long vendorId,
                              @RequestParam  Long [] menuId){
        Customer existingCustomer = customerService.getCustomer(customerId);
        Vendor existingVendor = vendorService.getVendor(vendorId);

        order.setCustomer(existingCustomer);
        order.setVendor(existingVendor);

        orderService.createOrder(order, existingVendor, menuId);

        return ("<h1> Order successfully added </h1>");

    }
}
