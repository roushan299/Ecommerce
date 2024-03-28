package com.example.Ecommerce.service;


import com.example.Ecommerce.dto.ProductDto;
import com.example.Ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    public List<ProductDto> getAllProducts() {
    }

    public ProductDto getProductById(Long id) {
    }

    public List<ProductDto> getProductByCategoryId(Long categoryId) {
    }

    public List<ProductDto> getProductByCategoryName(String categoryName) {
    }

    public ResponseEntity<Object> createProduct(ProductDto productRequest) {
    }

    public ResponseEntity<Object> updateProduct(Long id, ProductDto productRequest) {
    }

    public ResponseEntity<Object> deleteProduct(Long id) {
    }
}
