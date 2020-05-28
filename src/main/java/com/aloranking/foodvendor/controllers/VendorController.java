package com.aloranking.foodvendor.controllers;

import com.aloranking.foodvendor.exceptions.UserNotFoundException;
import com.aloranking.foodvendor.models.Menu;
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

    @PostMapping
    @RequestMapping("/register/vendor")
    public ResponseEntity<Vendor> create(@RequestBody final Vendor vendor) {
        vendorRepository.saveAndFlush(vendor);

        return new ResponseEntity<Vendor>(vendor, HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping("/vendor/all")
    public List<Vendor> listVendor() {
        return vendorRepository.findAll();
    }

    @GetMapping
    @RequestMapping("/vendor/{id}")
    public Vendor get(@PathVariable Long id) {
        return vendorRepository.getOne(id);
    }

    @PostMapping
    @RequestMapping("/home/vendor/{vendorId}/create-menu")
    public Menu createMenu(@PathVariable Long vendorId, @RequestBody Menu menu) {
        try {
            Vendor existingVendor = vendorService.getVendor(vendorId);
            menu.setVendor(existingVendor);
        } catch (Exception e) {
            throw new UserNotFoundException("Vendor with id  " + vendorId + "  does not exist ");
        }
        return menuRepository.saveAndFlush(menu);
    }

    @GetMapping
    @RequestMapping("/home/menus")
    public List<Menu> list() {
        return menuRepository.findAll();
    }


}
