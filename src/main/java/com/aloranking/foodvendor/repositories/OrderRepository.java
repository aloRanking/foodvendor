package com.aloranking.foodvendor.repositories;

import com.aloranking.foodvendor.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
