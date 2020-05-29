package com.aloranking.foodvendor.controllers;

import com.aloranking.foodvendor.exceptions.UserNotFoundException;
import com.aloranking.foodvendor.models.Menu;
import com.aloranking.foodvendor.models.Password;
import com.aloranking.foodvendor.models.Vendor;
import com.aloranking.foodvendor.repositories.MenuRepository;
import com.aloranking.foodvendor.repositories.VendorRepository;
import com.aloranking.foodvendor.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VendorController {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private VendorService vendorService;

    @GetMapping("/vendors")
    public List<Vendor> listVendors() {
        return vendorRepository.findAll();
    }

    @GetMapping("/vendors/{id}")
    public Vendor getVendor(@PathVariable Long id) {
        return vendorRepository.getOne(id);
    }

    @PostMapping("/register/vendor")
    public ResponseEntity<Vendor> createVendor(@RequestBody final Vendor vendor) {
        vendorRepository.saveAndFlush(vendor);
        return new ResponseEntity<>(vendor, HttpStatus.CREATED);
    }

    @PostMapping("/vendors/{id}/set-password")
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
