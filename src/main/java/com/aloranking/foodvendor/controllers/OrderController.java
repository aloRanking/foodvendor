package com.aloranking.foodvendor.controllers;

import com.aloranking.foodvendor.exceptions.UserNotFoundException;
import com.aloranking.foodvendor.models.*;
import com.aloranking.foodvendor.repositories.*;
import com.aloranking.foodvendor.services.CustomerService;
import com.aloranking.foodvendor.services.NotificationService;
import com.aloranking.foodvendor.services.OrderService;
import com.aloranking.foodvendor.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    NotificationService notificationService;

    @GetMapping
    public List<Order> listOrders(){
        return orderRepository.findAll();

    }

    @GetMapping("{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderRepository.getOne(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getAllCustomerOrder(@PathVariable Long customerId){

            Customer existingCustomer = customerService.getCustomer(customerId);
        return orderService.getAllOrdersForCustomer(existingCustomer);

    }

    @GetMapping("/vendor/{vendorId}/")
    public List<Order> getAllVendorOrder(@PathVariable Long vendorId){
        Vendor existingVendor = vendorService.getVendor(vendorId);
        return orderService.getAllOrdersForVendor(existingVendor);
    }



    @PostMapping("/customer/{customerId}/create-order/vendor/{vendorId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long customerId,
                                      @RequestBody Order order, @PathVariable Long vendorId,
                                      @RequestParam  Long [] menuId)  {
          Customer existingCustomer = customerService.getCustomer(customerId);
          order.setCustomer(existingCustomer);
           Vendor existingVendor = vendorService.getVendor(vendorId);
           order.setVendor(existingVendor);
          Order order1= orderService.createOrder(order, existingVendor,existingCustomer, menuId);
          notificationService.sendNotification(order1,existingVendor,existingCustomer,menuId);

        return new ResponseEntity<>(order, HttpStatus.CREATED);

    }


    @PutMapping("/vendor/update-order/{orderId}")
    public Order vendorUpdateOrder(@PathVariable Long orderId, @RequestParam String orderstatus){
        Order order = orderService.getOrder(orderId);
        String mssg = orderstatus.toUpperCase();
        OrderStatus message = orderStatusRepository.findByOrderStatus(mssg);
        order.setOrder_status(message);
        notificationService.sendOrderUpdateNotification(order,order.getVendor(),order.getCustomer(), mssg);
        return orderRepository.save(order);

    }

    @PutMapping("/customer/update-order/{orderId}")
    public Order customerUpdateOrder(@PathVariable Long orderId, @RequestParam String orderstatus){
        Order order = orderService.getOrder(orderId);
        String mssg = orderstatus.toUpperCase();
        if (mssg.equals("ACCEPTED") || mssg.equals("PENDING") || mssg.equals("READY"))
            throw new UserNotFoundException("Customer can only cancel order using the word \"cancelled");
        OrderStatus message = orderStatusRepository.findByOrderStatus(mssg);
        order.setOrder_status(message);

        return orderRepository.save(order);

    }
}
