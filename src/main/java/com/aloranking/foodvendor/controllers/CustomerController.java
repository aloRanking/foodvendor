package com.aloranking.foodvendor.controllers;

import com.aloranking.foodvendor.exceptions.UserNotFoundException;
import com.aloranking.foodvendor.models.Customer;
import com.aloranking.foodvendor.models.Password;
import com.aloranking.foodvendor.repositories.CustomerRepository;
import com.aloranking.foodvendor.services.CustomerService;
import com.sun.xml.bind.v2.util.QNameMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;


    @PostMapping( "/register")
    public ResponseEntity<Customer> createCustomer(@RequestBody final Customer customer){
        customerRepository.saveAndFlush(customer);

        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Customer> listCustomers(){
        return customerRepository.findAll();
    }


    @GetMapping( "{id}")
    public Customer getCustomer(@PathVariable Long id){
        return customerRepository.getOne(id);
    }


    @PostMapping( "{id}/set-password")
    public ResponseEntity addCustomerUser(@PathVariable("id") Long id, @RequestBody Password password) {
        try {
            Customer existingCustomer = customerService.getCustomer(id);
            Customer customer = customerService.addUser(existingCustomer, password.getPassword());
        } catch (Exception e) {
            throw new UserNotFoundException("Customer with id  " + id + "  does not exist ");
        }
        return new ResponseEntity("<h1>Password Set Successfully</h1>", HttpStatus.ACCEPTED);
    }
}
