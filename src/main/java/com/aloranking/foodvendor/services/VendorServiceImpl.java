package com.aloranking.foodvendor.services;

import com.aloranking.foodvendor.models.AuthUser;
import com.aloranking.foodvendor.models.Role;
import com.aloranking.foodvendor.models.Vendor;
import com.aloranking.foodvendor.repositories.AuthUserRepository;
import com.aloranking.foodvendor.repositories.RoleRepository;
import com.aloranking.foodvendor.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Vendor addUser(Vendor vendor, String password) {
        AuthUser authUser= createUser(vendor.getEmail(), password, vendor);
        //authUser.setCustomer(customer);
        vendor.setAuthUser(authUser);

        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor getVendor(Long id) {

        return vendorRepository.getOne(id);
    }

    public AuthUser createUser(String email, String password, Vendor vendor){
        AuthUser authUser = new AuthUser();
        authUser.setEmail(email);
        authUser.setPassword(password);
        authUser.setVendor(vendor);
        Role role = roleRepository.findByRole("VENDOR");
          authUser.setRole(role);
        authUserRepository.save(authUser);
        return authUser;
    }



}
