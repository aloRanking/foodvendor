package com.aloranking.foodvendor.controllers;

import com.aloranking.foodvendor.models.Menu;
import com.aloranking.foodvendor.models.Vendor;
import com.aloranking.foodvendor.repositories.MenuRepository;
import com.aloranking.foodvendor.repositories.VendorRepository;
import com.aloranking.foodvendor.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping("/home/{id}/create-menu")
    public Menu createMenu(@PathVariable Long id, @RequestBody Menu menu){
        Vendor existingVendor = vendorService.getVendor(id);
        menu.setVendor(existingVendor);
        return  menuRepository.saveAndFlush(menu);


    }

    @GetMapping
    @RequestMapping("/menus")
    public List<Menu> list(){
return menuRepository.findAll();
    }


}
