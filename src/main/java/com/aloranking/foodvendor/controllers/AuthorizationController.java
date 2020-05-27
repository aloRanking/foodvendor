package com.aloranking.foodvendor.controllers;

import com.aloranking.foodvendor.exceptions.UserNotFoundException;
import com.aloranking.foodvendor.models.AuthUser;
import com.aloranking.foodvendor.models.Customer;
import com.aloranking.foodvendor.models.Password;
import com.aloranking.foodvendor.models.Vendor;
import com.aloranking.foodvendor.repositories.AuthUserRepository;
import com.aloranking.foodvendor.repositories.CustomerRepository;
import com.aloranking.foodvendor.repositories.VendorRepository;
import com.aloranking.foodvendor.services.CustomerService;
import com.aloranking.foodvendor.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AuthorizationController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private VendorRepository vendorRepository;


    @GetMapping
    @RequestMapping("/user/{id}")
    public AuthUser fetch(@PathVariable Long id) {
        return authUserRepository.getOne(id);
    }



    @GetMapping
    @RequestMapping("/home")
    public String home() {
        return ("<h1> Welcome Home</h1>");
    }



    @PostMapping
    @RequestMapping("/customer/{id}/set-password")
    public ResponseEntity addUser(@PathVariable("id") Long id, @RequestBody Password password) {
        try {
            Customer existingCustomer = customerService.getCustomer(id);
            Customer customer = customerService.addUser(existingCustomer, password.getPassword());
        } catch (Exception e) {
            throw new UserNotFoundException("Customer with id  " + id + "  does not exist ");
        }
        return new ResponseEntity("<h1>Password Set Successfully</h1>", HttpStatus.ACCEPTED);
    }

    @PostMapping
    @RequestMapping("vendor/{id}/set-password")
    public String addVendorUser(@PathVariable("id") Long id, @RequestBody Password password) {
        try {
            Vendor existingVendor = vendorService.getVendor(id);
            Vendor vendor = vendorService.addUser(existingVendor, password.getPassword());
        } catch (Exception e) {
            throw new UserNotFoundException("Vendor with id  " + id + "  does not exist ");
        }
        return ("<h1>Password Set Successfully</h1>");
    }


}
