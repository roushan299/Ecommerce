package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.ProductDto;
import com.example.Ecommerce.exceptions.NoCategoryExitsException;
import com.example.Ecommerce.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/home")
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping
    public HashMap<String, List<ProductDto>> getAllProducts(){
        HashMap<String, List<ProductDto>> map = null;
        try {
            map = homeService.getAllProducts();
        } catch (NoCategoryExitsException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @GetMapping(path = "/search/{keyword}")
    public HashMap<String, List<ProductDto>> getProductsByKeyword(@PathVariable String keyword){
        HashMap<String, List<ProductDto>> map = null;
        try {
            map = homeService.getProductsByKeyword(keyword);
        } catch (NoCategoryExitsException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

}
