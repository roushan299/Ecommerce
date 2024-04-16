package com.example.Ecommerce.service;

import com.example.Ecommerce.exceptions.NoCategoryExitsException;
import com.example.Ecommerce.model.Category;
import com.example.Ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Set<Category> saveCategoriesAndUpdateInSet(List<String> categories) {
        Set<Category> newCategories = new HashSet<>();
        for(String categoryName: categories){
            Optional<Category> optionalCategory = categoryRepository.findByName(categoryName);
            if(optionalCategory.isPresent()){
               Category category = optionalCategory.get();
                newCategories.add(category);
            }else {
                Category category = Category.builder().name(categoryName).build();
                category = categoryRepository.save(category);
                newCategories.add(category);
            }
        }
        return newCategories;
    }

    public Category getCategoryById(Long id) throws NoCategoryExitsException {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()){
            throw new NoCategoryExitsException("No category exits with id: "+id);
        }
        Category category = optionalCategory.get();
        return category;
    }

    public Category getCategoryByName(String name) throws NoCategoryExitsException {
        Optional<Category> optionalCategory = categoryRepository.findByName(name);
        if(optionalCategory.isEmpty()){
            throw new NoCategoryExitsException("No category exits with Name: "+name);
        }
        Category category = optionalCategory.get();
        return category;
    }

    public ResponseEntity<Object> addCategory(String categoryRequest) {
        ResponseEntity<Object> response;
        try {
            Category category = getCategoryByName(categoryRequest);
            response = new ResponseEntity<>("category with name "+categoryRequest+" exits already", HttpStatus.BAD_REQUEST);
        } catch (NoCategoryExitsException e) {
            Category category = Category.builder()
                    .name(categoryRequest)
                    .build();
            categoryRepository.save(category);
            response = new ResponseEntity<>("category added", HttpStatus.CREATED);
        }
        return response;
    }

    public List<String> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(this::mapStringToCategory).collect(Collectors.toList());
    }

    public ResponseEntity<Object> deleteCategory(Long id) {
        ResponseEntity<Object> response;
        try {
            Category category = getCategoryById(id);
            categoryRepository.delete(category);
            response = new ResponseEntity<>("Category deleted", HttpStatus.OK);
        } catch (NoCategoryExitsException e) {
            response = new ResponseEntity<>("No category exits with id: "+id, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    public ResponseEntity<Object> updateCategory(Long id, String categoryRequest) {
        ResponseEntity<Object> response;
        try {
            Category category = getCategoryById(id);
            category.setName(categoryRequest);
            categoryRepository.save(category);
            response = new ResponseEntity<>("Category is updated", HttpStatus.OK);
        } catch (NoCategoryExitsException e) {
            response = new ResponseEntity<>("NO suc categor exits with id: "+id, HttpStatus.BAD_REQUEST);
        }
        return response;
    }
    private String mapStringToCategory(Category category) {
        return category.getName();
    }

    public List<String> getCategoryByKeyword(String keyword) {
        List<Category> categoryList = categoryRepository.findCategoriesByNameKeyword(keyword);
        if(categoryList.isEmpty()){

        }
        return categoryList.stream().map(this::mapStringToCategory).collect(Collectors.toList());
    }

}
