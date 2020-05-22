package com.aloranking.foodvendor.repositories;

import com.aloranking.foodvendor.models.Customer;
import com.aloranking.foodvendor.models.Order;
import com.aloranking.foodvendor.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    public List<Order> findByCustomer(Customer customer);

    public List<Order> findByVendor(Vendor vendor);

}
