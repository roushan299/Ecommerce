package com.example.Ecommerce.controller;


import com.example.Ecommerce.dto.ProductDto;
import com.example.Ecommerce.exceptions.NoCategoryExitsException;
import com.example.Ecommerce.exceptions.ProductNotExitsException;
import com.example.Ecommerce.service.ProductService;
import lombok.SneakyThrows;
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
        ProductDto product = null;
        try {
            product = productService.getProductById(id);
        } catch (ProductNotExitsException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @GetMapping("/{categoryId}")
    public List<ProductDto> getProductByCategoryId(@PathVariable Long categoryId){
        List<ProductDto> productList = null;
        try {
            productList = productService.getProductByCategoryId(categoryId);
        } catch (NoCategoryExitsException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @GetMapping("/{categoryName}")
    public List<ProductDto> getProductByCategoryName(@PathVariable String categoryName){
        List<ProductDto> productList = null;
        try {
            productList = productService.getProductByCategoryName(categoryName);
        } catch (NoCategoryExitsException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody ProductDto productRequest){
        ResponseEntity<Object> response = productService.createProduct(productRequest);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id,@RequestBody ProductDto productRequest){
        ResponseEntity<Object> response = null;
        try {
            response = productService.updateProduct(id, productRequest);
        } catch (ProductNotExitsException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id)  {
        ResponseEntity<Object> response = null;
        try {
            response = productService.deleteProduct(id);
        } catch (ProductNotExitsException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

}
