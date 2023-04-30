package com.mvcjava.ecomm.repository;

import com.mvcjava.ecomm.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer>
{
    List<Order> getAllByUsername(String name);
}
