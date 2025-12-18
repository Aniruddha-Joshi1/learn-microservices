package com.microservices.product_service.controller;

import com.microservices.product_service.dto.ProductRequest;
import com.microservices.product_service.dto.ProductResponse;
import com.microservices.product_service.model.Product;
import com.microservices.product_service.service.ProductService;
import com.microservices.product_service.service.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequest productRequest){
        try{
            productService.createProduct(productRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
        catch (Exception e){
            log.error("Failed to create the product: {}", productRequest.getProductName());
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        try{
            List<ProductResponse> allProducts = productService.getAllProducts();
            return ResponseEntity.status(HttpStatus.OK).body(allProducts);
        } catch(Exception e){
            log.error("Failed to fetch all the products: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
