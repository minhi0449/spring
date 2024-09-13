package com.ch07.repository.shop.custom;

import com.ch07.entity.shop.Customer;

import java.util.List;

// CustomerRepository 확장 인터페이스 정의
public interface CustomerRepositoryCustom {

    public List<Customer> selectCustomers();

    public static Customer selectCustomer(String custId) {
        return null;
    }

    Customer selectCustomer(String custId);

    public List<Customer> searchCustomer(String nameCondition, int ageCondition);
    public List<Customer> searchKeyword(String keyword);
}