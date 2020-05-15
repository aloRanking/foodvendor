package com.aloranking.foodvendor.controllers;

import com.aloranking.foodvendor.models.AuthUser;
import com.aloranking.foodvendor.models.Customer;
import com.aloranking.foodvendor.models.Password;
import com.aloranking.foodvendor.repositories.AuthUserRepository;
import com.aloranking.foodvendor.repositories.CustomerRepository;
import com.aloranking.foodvendor.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorizationController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private CustomerService customerService;


    @PostMapping
    @RequestMapping("/register/user")
    public String create(@RequestBody AuthUser authUser){
        Customer customer = new Customer();
        customer.setEmail("kido@gmail.com");
        customer.setFirst_name("kido");
        customer.setLast_name("Noffi");
        customer.setPhone_number("12345");
        customer.setAmount_outstanding(45.0);

        customer.setAuthUser(authUser);
        authUser.setCustomer(customer);


        authUserRepository.save(authUser);
        return ("<h1> User saved </h1>");
    }

    @GetMapping
    @RequestMapping("/user/{id}")
    public AuthUser fetch(@PathVariable Long id){
        return authUserRepository.getOne(id);
    }


    @GetMapping
    @RequestMapping("/customer/get-all")
    public List<Customer> list(){
        return customerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("/customer/{id}")
    public Customer get(@PathVariable Long id){
        return customerRepository.getOne(id);
    }


    @PostMapping
    @RequestMapping("/register/customer")
    public String create(@RequestBody final Customer customer){



        customerRepository.saveAndFlush(customer);
        return "Successfully Register";

    }

    @PostMapping
    @RequestMapping("{id}/set-password")
    public String addUser(@PathVariable("id") Long id, @RequestBody Password password){
        Customer existingCustomer = customerService.getCustomer(id);//.orElseThrow(() -> new UserNotFoundException(id));
        Customer customer = customerService.addUser(existingCustomer, password.getPassword());

        return "Successfully updated";
    }


}
