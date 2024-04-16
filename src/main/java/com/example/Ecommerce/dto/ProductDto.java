package com.example.Ecommerce.dto;


import com.example.Ecommerce.model.Category;
import lombok.*;

import java.util.Collections;
import java.util.List;
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
    private List<String> categories;
    public List<String> getCategories(){
        return categories != null ? categories : Collections.emptyList();
    }


}
