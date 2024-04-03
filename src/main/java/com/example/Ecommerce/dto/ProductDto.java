package com.example.Ecommerce.dto;


import com.example.Ecommerce.model.Category;
import lombok.*;

import java.util.Collections;
import java.util.Set;

@Data
@Builder
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String name;
    private double price;
    private String description;
    private Set<Category> categories;
    public Set<Category> getCategories(){
        return categories != null ? categories : Collections.emptySet();
    }


}
