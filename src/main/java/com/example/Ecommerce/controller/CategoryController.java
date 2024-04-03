package com.example.Ecommerce.controller;


import com.example.Ecommerce.exceptions.NoCategoryExitsException;
import com.example.Ecommerce.model.Category;
import com.example.Ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Object> addCategory(@RequestBody String categoryRequest){
        ResponseEntity<Object> response = categoryService.addCategory(categoryRequest);
        return response;
    }

    @GetMapping("/{id}")
    public String getCategoryBId(@PathVariable Long id){
        try {
            Category category = categoryService.getCategoryById(id);
            return category.getName();
        } catch (NoCategoryExitsException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<String> getAllCategory(){
        List<String> categoryList = categoryService.getAllCategory();
        return categoryList;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Long id, @RequestBody String categoryRequest){
        ResponseEntity<Object> response = categoryService.updateCategory(id, categoryRequest);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id){
       ResponseEntity<Object> response = categoryService.deleteCategory(id);
       return response;
    }

}
