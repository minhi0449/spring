package com.ch07.repository.shop;

import com.ch07.entity.shop.Customer;
import com.ch07.repository.shop.custom.CustomerRepositoryCustom;
import com.ch07.repository.shop.impl.CustomerRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String>, CustomerRepositoryCustom {
}