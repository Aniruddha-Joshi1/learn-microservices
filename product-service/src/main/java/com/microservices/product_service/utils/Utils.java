package com.microservices.product_service.utils;

import com.microservices.product_service.dto.ProductRequest;
import com.microservices.product_service.dto.ProductResponse;
import com.microservices.product_service.model.Product;

public class Utils {
    public ProductResponse mapToProductResponse(Product product){
        // Builder pattern
        return ProductResponse.builder()
                .productName(product.getProductName())
                .productId(product.getProductId())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }

    public Product mapToProduct(ProductRequest productRequest){
        // Builder pattern
        return Product.builder()
                .productName(productRequest.getProductName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
    }
}
