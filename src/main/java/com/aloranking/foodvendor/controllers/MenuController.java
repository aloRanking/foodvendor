package com.aloranking.foodvendor.controllers;

import com.aloranking.foodvendor.exceptions.UserNotFoundException;
import com.aloranking.foodvendor.models.Menu;
import com.aloranking.foodvendor.models.Vendor;
import com.aloranking.foodvendor.repositories.MenuRepository;
import com.aloranking.foodvendor.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private VendorService vendorService;

    @GetMapping("/menus")
    public List<Menu> listMenu() {
        return menuRepository.findAll();
    }

    @GetMapping("/menus/{id}")
    public Menu getMenu(@PathVariable Long id) {
        return menuRepository.getOne(id);
    }

    @PostMapping("/vendor/{vendorId}/create-menu")
    public Menu createMenu(@PathVariable Long vendorId, @RequestBody Menu menu) {
        try {
            Vendor existingVendor = vendorService.getVendor(vendorId);
            menu.setVendor(existingVendor);
        } catch (Exception e) {
            throw new UserNotFoundException("Vendor with id  " + vendorId + "  does not exist ");
        }
        return menuRepository.saveAndFlush(menu);
    }
}
