package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.ProductResponse;
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
    public HashMap<String, List<ProductResponse>> getAllProducts(){
        HashMap<String, List<ProductResponse>> map = homeService.getAllProducts();
        return map;
    }

    @GetMapping(path = "/search/{keyword}")
    public HashMap<String, List<ProductResponse>> getProductsByKeyword(@PathVariable String keyword){
        HashMap<String, List<ProductResponse>> map = homeService.getProductsByKeyword();
        return map;
    }
}
