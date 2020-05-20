package com.aloranking.foodvendor.services;

import com.aloranking.foodvendor.models.Menu;
import com.aloranking.foodvendor.models.Order;
import com.aloranking.foodvendor.models.Vendor;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    public Order createOrder(Order order, Vendor vendor, Long [] menuId);


}
