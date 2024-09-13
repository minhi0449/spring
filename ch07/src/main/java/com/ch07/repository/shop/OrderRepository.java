package com.ch07.repository.shop;

import com.ch07.entity.shop.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}