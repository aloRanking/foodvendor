package com.aloranking.foodvendor.controllers;

import com.aloranking.foodvendor.models.Menu;
import com.aloranking.foodvendor.models.Vendor;
import com.aloranking.foodvendor.repositories.MenuRepository;
import com.aloranking.foodvendor.repositories.VendorRepository;
import com.aloranking.foodvendor.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendorController {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private VendorService vendorService;

    /*@PostMapping
    @RequestMapping("/home/create-menu")
    public Menu create(@RequestBody Menu menu){
        Vendor existingVendor = vendorService.getVendor(id);
        men


    }*/


}
