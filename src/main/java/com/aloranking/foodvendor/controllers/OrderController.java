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
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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

    @GetMapping("/home/{customerId}/orders")
    public List<Order> getCustomerOrder(@PathVariable Long customerId){

            Customer existingCustomer = customerService.getCustomer(customerId);
           // if (existingCustomer == null)  throw new UserNotFoundException("Customer with id  " + customerId + "  does not exist ");
            List<Order> orders = orderService.getAllOrdersForCustomer(existingCustomer);
            return orders;

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

   /* @PatchMapping
    @RequestMapping("/home/order/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderId, @RequestBody Map<String, Object> fields){
       // Order order = orderService.getOrder(orderId);
        // Map key is field name, v is value






    }*/
}
