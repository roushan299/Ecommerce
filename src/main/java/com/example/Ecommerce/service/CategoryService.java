package com.example.Ecommerce.service;


import com.example.Ecommerce.exceptions.NoCategoryExitsException;
import com.example.Ecommerce.model.Category;
import com.example.Ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Set<Category> saveCategoriesAndUpdateInSet(Set<Category> categories) {
        Set<Category> newCategories = new HashSet<Category>();
        for(Category category: categories){
            String categoryName = category.getName();
            Optional<Category> optionalCategory = categoryRepository.findByName(categoryName);
            if(optionalCategory.isPresent()){
                category = optionalCategory.get();
            }else {
                category = categoryRepository.save(category);

            }
            newCategories.add(category);
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
            throw new NoCategoryExitsException("No category exits with id: "+name);
        }
        Category category = optionalCategory.get();
        return category;
    }

}
