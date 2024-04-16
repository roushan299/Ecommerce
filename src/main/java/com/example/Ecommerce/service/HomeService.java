package com.example.Ecommerce.service;

//import com.example.Ecommerce.dto.ProductResponse;
import com.example.Ecommerce.dto.ProductDto;
import com.example.Ecommerce.exceptions.NoCategoryExitsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HomeService {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;


    public HashMap<String, List<ProductDto>> getAllProducts() throws NoCategoryExitsException {
        List<String> categoryList = categoryService.getAllCategory();
        HashMap<String, List<ProductDto>> map = getProductsByCategory(categoryList);
        return map;
    }

    public HashMap<String, List<ProductDto>> getProductsByKeyword(String keyword) throws NoCategoryExitsException {
        List<String> categoryList = categoryService.getCategoryByKeyword(keyword);
        HashMap<String, List<ProductDto>> map = getProductsByCategory(categoryList);
        return map;
    }

    public HashMap<String, List<ProductDto>> getProductsByCategory(List<String> categoryList) throws NoCategoryExitsException {
        HashMap<String, List<ProductDto>> map = new HashMap<>();
        for(String categoryName : categoryList) {
            if(!map.containsKey(categoryName)) {
                List<ProductDto> productList = productService.getProductByCategoryName(categoryName);
                map.put(categoryName, productList);
            }
        }
        return map;
    }
}
