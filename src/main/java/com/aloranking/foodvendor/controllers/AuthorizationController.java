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
    private AuthUserRepository authUserRepository;

    @GetMapping(value = "/home")
    public String home() {
        return ("<h1> Welcome Home</h1>");
    }

    @GetMapping(value = "/users")
    public List<AuthUser> listAuthUsers(){
        return authUserRepository.findAll();
    }

    @GetMapping(value = "/users/{id}")
    public AuthUser getUser(@PathVariable Long id) {
        return authUserRepository.getOne(id);
    }














}
