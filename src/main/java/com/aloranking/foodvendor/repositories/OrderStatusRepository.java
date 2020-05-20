package com.aloranking.foodvendor.repositories;

import com.aloranking.foodvendor.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {



    public OrderStatus findByOrderStatus(String mssg);
}
