package com.ch07.entity.shop;

import jakarta.persistence.Table;

@Table(name = "shop_order_item")
public class OrderItem {
    private int orderItemId;
    private Order order;
    private Product product;
    private int count;
}
