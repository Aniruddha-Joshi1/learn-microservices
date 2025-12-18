package com.microservices.product_service.service.impl;

import com.microservices.product_service.dto.ProductRequest;
import com.microservices.product_service.dto.ProductResponse;
import com.microservices.product_service.model.Product;
import com.microservices.product_service.repository.ProductRepository;
import com.microservices.product_service.service.ProductService;
import com.microservices.product_service.utils.Utils;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Builder
@Slf4j
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    Utils utils;

    // This can be removed if we add @RequiredArgsConstructor annotation, it will take care of initialising
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public void createProduct(ProductRequest productRequest) {
        try{
            Product product = utils.mapToProduct(productRequest);

            productRepository.save(product);
            log.info("Product with id: {} and name: {} is saved", product.getProductId(), product.getProductName());
        } catch(Exception e){
            log.error("Could not save the product: {}", productRequest.getProductName());
            log.error("Error: {}", e.getMessage());
        }
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream()
                .map(utils::mapToProductResponse)
                .toList();
    }
}
