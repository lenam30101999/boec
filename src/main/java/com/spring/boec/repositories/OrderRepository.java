package com.spring.boec.repositories;

import com.spring.boec.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findTopByCustomerIdOrderByIdDesc(int customerId);
    Order findById(int id);
}
