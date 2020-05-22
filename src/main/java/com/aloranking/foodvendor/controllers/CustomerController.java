package com.aloranking.foodvendor.controllers;

import com.aloranking.foodvendor.models.Customer;
import com.aloranking.foodvendor.repositories.CustomerRepository;
import com.sun.xml.bind.v2.util.QNameMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    @RequestMapping("/register/customer")
    public ResponseEntity<Customer> create(@RequestBody final Customer customer){
        customerRepository.saveAndFlush(customer);

        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping("/customer/all")
    public List<Customer> list(){
        return customerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("/customer/{id}")
    public Customer get(@PathVariable Long id){
        return customerRepository.getOne(id);
    }
}
