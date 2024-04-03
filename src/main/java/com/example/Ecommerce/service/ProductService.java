package com.example.Ecommerce.service;


import com.example.Ecommerce.dto.ProductDto;
import com.example.Ecommerce.exceptions.NoCategoryExitsException;
import com.example.Ecommerce.exceptions.ProductNotExitsException;
import com.example.Ecommerce.model.Category;
import com.example.Ecommerce.model.Product;
import com.example.Ecommerce.repository.CategoryRepository;
import com.example.Ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;


    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(this::mapProductToProductDto).collect(Collectors.toList());
    }


    public ProductDto getProductById(Long id) throws ProductNotExitsException {
        Product product = this.getProductByIdForInternal(id);
        ProductDto productResponse = this.mapProductToProductDto(product);
        return productResponse;
    }

    public List<ProductDto> getProductByCategoryId(Long categoryId) throws NoCategoryExitsException {
        //check categoryExits
        Category category = categoryService.getCategoryById(categoryId);
        List<Product> productList = setToListConverter(category.getProducts());
        return productList.stream().map(this::mapProductToProductDto).collect(Collectors.toList());
    }

    public List<ProductDto> getProductByCategoryName(String categoryName) throws NoCategoryExitsException {
        Category category = categoryService.getCategoryByName(categoryName);
        List<Product> productList = setToListConverter(category.getProducts());
        return productList.stream().map(this::mapProductToProductDto).collect(Collectors.toList());
    }

    public ResponseEntity<Object> createProduct(ProductDto productRequest) {
            //check product already exits or not
        Set<Category> categories = productRequest.getCategories();
        categories = categoryService.saveCategoriesAndUpdateInSet(categories);
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .categories(categories)
                .build();

        productRepository.save(product);

        ResponseEntity<Object> response = new ResponseEntity<>("Product saved", HttpStatus.OK);
        return response;

    }


    public ResponseEntity<Object> updateProduct(Long id, ProductDto productRequest) throws ProductNotExitsException {
        Product product = this.getProductByIdForInternal(id);
        Set<Category> categories = categoryService.saveCategoriesAndUpdateInSet(productRequest.getCategories());
        product.setDescription(productRequest.getDescription());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setCategories(categories);

        productRepository.save(product);
        ResponseEntity<Object> response = new ResponseEntity<>("Product updated", HttpStatus.OK);
        return response;
    }

    public ResponseEntity<Object> deleteProduct(Long id) throws ProductNotExitsException {
        Product product = this.getProductByIdForInternal(id);
        productRepository.delete(product);
        ResponseEntity<Object> response = new ResponseEntity<>("Product deleted with id: "+id, HttpStatus.OK);
        return response;
    }

    private ProductDto mapProductToProductDto(Product product) {
        ProductDto productDto = ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
        return productDto;
    }

    private List<Product> setToListConverter(Set<Product> productSet){
        List<Product> productList = new ArrayList<>();
        productSet.addAll(productSet);
        return productList;
    }

    public Product getProductByIdForInternal(Long id) throws ProductNotExitsException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotExitsException("Product doest exits with id: "+id);
        }
        Product product = optionalProduct.get();
        return product;
    }

}
