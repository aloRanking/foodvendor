package com.aloranking.foodvendor.services;

import com.aloranking.foodvendor.models.*;
import com.aloranking.foodvendor.repositories.*;
import org.aspectj.weaver.ast.Or;
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
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    NotificationMessageRepository notificationMessageRepository;
    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Order createOrder(Order order, Vendor vendor,  Customer customer, Long [] menuId) {

        List<Long> menus = Arrays.asList(menuId);
        Iterable<Menu> menu = menuRepository.findByIdIn(menus);
        order.setItems_ordered((List<Menu>) menu);
        OrderStatus message = orderStatusRepository.findByOrderStatus("PENDING");
        order.setOrder_status(message);
        return orderRepository.save(order);
    }


    @Override
    public Order getOrder(Long id) {
       return orderRepository.getOne(id);
    }

    @Override
    public List<Order> getAllOrdersForCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }

    @Override
    public List<Order> getAllOrdersForVendor(Vendor vendor) {
        return orderRepository.findByVendor(vendor);
    }
}
