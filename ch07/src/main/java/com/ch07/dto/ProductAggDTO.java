package com.ch07.dto;

import lombok.Data;

@Data
public class ProductAggDTO {
    // Agg 집계 DTO
    private int priceSum;
    private double priceAvg;
    private int priceMax;
    private int priceMin;

}
