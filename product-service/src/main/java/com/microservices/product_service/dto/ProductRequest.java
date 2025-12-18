package com.microservices.product_service.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductRequest {
    private String productName;
    private String description;
    private BigDecimal price;
}
