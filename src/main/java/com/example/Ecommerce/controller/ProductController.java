package com.example.Ecommerce.controller;


import com.example.Ecommerce.dto.ProductDto;
import com.example.Ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts(){
        List<ProductDto> productList = productService.getAllProducts();
        return productList;
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        ProductDto product = productService.getProductById(id);
        return product;
    }

    @GetMapping("/{categoryId}")
    public List<ProductDto> getProductByCategoryId(@PathVariable Long categoryId){
        List<ProductDto> productList = productService.getProductByCategoryId(categoryId);
        return productList;
    }

    @GetMapping("/{categoryName}")
    public List<ProductDto> getProductByCategoryName(@PathVariable String categoryName){
        List<ProductDto> productList = productService.getProductByCategoryName(categoryName);
        return productList;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody ProductDto productRequest){
        ResponseEntity<Object> response = productService.createProduct(productRequest);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id,@RequestBody ProductDto productRequest){
        ResponseEntity<Object> response = productService.updateProduct(id, productRequest);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id){
        ResponseEntity<Object> response = productService.deleteProduct(id);
        return response;
    }

}
