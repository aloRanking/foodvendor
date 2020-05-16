package com.aloranking.foodvendor.repositories;

import com.aloranking.foodvendor.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
