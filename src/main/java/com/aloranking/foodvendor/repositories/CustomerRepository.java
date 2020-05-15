package com.aloranking.foodvendor.repositories;

import com.aloranking.foodvendor.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  //  public Customer findByEmail(String email);

    //void errorMessage(String s);
}

