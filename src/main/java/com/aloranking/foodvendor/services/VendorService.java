package com.aloranking.foodvendor.services;

import com.aloranking.foodvendor.models.Customer;
import com.aloranking.foodvendor.models.Vendor;
import org.springframework.stereotype.Service;

@Service
public interface VendorService {

    public Vendor addUser(Vendor vendor, String password);

    public Vendor getVendor(Long id);
}
