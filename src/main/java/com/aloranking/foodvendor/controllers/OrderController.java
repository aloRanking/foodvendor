package com.aloranking.foodvendor.controllers;

import com.aloranking.foodvendor.exceptions.UserNotFoundException;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.util.ReflectionUtils.findField;

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

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @GetMapping
    @RequestMapping("/home/orders")
    public List<Order> getAll(){

        return orderRepository.findAll();

    }

/*    @GetMapping
    @RequestMapping("/home/orders")
    public CollectionModel<Order> all(){

       // return orderRepository.findAll();

        List<EntityModel<Order>> orders = orderRepository.findAll().stream()
                .map(order -> new EntityModel<>(order,
                        linkTo(OrderController.class).slash(order.getId()).withSelfRel(),
                        linkTo(OrderController.class).withRel("orders")))
                .collect(Collectors.toList());

        return new EntityModel<Order>(orders,
                linkTo(methodOn(OrderController.class)).all()).withSelfRel();

    }*/

    @GetMapping("/home/customer/{customerId}/orders")
    public List<Order> getCustomerOrder(@PathVariable Long customerId){

            Customer existingCustomer = customerService.getCustomer(customerId);
           // if (existingCustomer == null)  throw new UserNotFoundException("Customer with id  " + customerId + "  does not exist ");
            List<Order> allOrdersOFCustomer = orderService.getAllOrdersForCustomer(existingCustomer);
            return allOrdersOFCustomer;

    }

    @GetMapping("/home/vendor/{vendorId}/orders")
    public List<Order> getVendorOrder(@PathVariable Long vendorId){
        Vendor existingVendor = vendorService.getVendor(vendorId);

        List<Order> allOrdersForVendor =orderService.getAllOrdersForVendor(existingVendor);
        return allOrdersForVendor;
    }



    @PostMapping
    @RequestMapping("home/{customerId}/create-order/{vendorId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long customerId,
                                      @RequestBody Order order, @PathVariable Long vendorId,
                                      @RequestParam  Long [] menuId)  {

          Customer existingCustomer = customerService.getCustomer(customerId);
          if (existingCustomer == null){
              throw new UserNotFoundException("Customer with id  " + customerId + "  does not exist ");
          }
          order.setCustomer(existingCustomer);
           Vendor existingVendor = vendorService.getVendor(vendorId);
           if (existingVendor==null)
               throw new UserNotFoundException("Vendor with id  " + vendorId + "  does not exist ");

           order.setVendor(existingVendor);
           orderService.createOrder(order, existingVendor, menuId);

        return new ResponseEntity<Order>(order, HttpStatus.CREATED);

    }


    @RequestMapping("/home/vendor/update-order/{orderId}")
    public Order vendorUpdateOrder(@PathVariable Long orderId, @RequestParam String orderstatus){
        Order order = orderService.getOrder(orderId);
        String mssg = orderstatus.toUpperCase();
        OrderStatus message = orderStatusRepository.findByOrderStatus(mssg);
        order.setOrder_status(message);

        return orderRepository.save(order);

    }
}
