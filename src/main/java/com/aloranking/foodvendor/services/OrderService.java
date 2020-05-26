package com.aloranking.foodvendor.services;

import com.aloranking.foodvendor.models.Customer;
import com.aloranking.foodvendor.models.Menu;
import com.aloranking.foodvendor.models.Order;
import com.aloranking.foodvendor.models.Vendor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    public Order createOrder(Order order, Vendor vendor, Customer customer, Long [] menuId);

    public Order getOrder(Long id);

    public List<Order> getAllOrdersForCustomer (Customer customer);


    public List<Order> getAllOrdersForVendor(Vendor vendor);
}
