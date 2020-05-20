package com.aloranking.foodvendor.services;

import com.aloranking.foodvendor.models.Menu;
import com.aloranking.foodvendor.models.Order;
import com.aloranking.foodvendor.models.OrderStatus;
import com.aloranking.foodvendor.models.Vendor;
import com.aloranking.foodvendor.repositories.MenuRepository;
import com.aloranking.foodvendor.repositories.OrderRepository;
import com.aloranking.foodvendor.repositories.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Order createOrder(Order order, Vendor vendor, Long [] menuId) {

        List<Long> menus = Arrays.asList(menuId);

        Iterable<Menu> menu = menuRepository.findByIdIn(menus);
        order.setItems_ordered((List<Menu>) menu);
        OrderStatus message = orderStatusRepository.findByOrderStatus("Pending");


        order.setOrder_status(message);
        return orderRepository.save(order);
    }
}
