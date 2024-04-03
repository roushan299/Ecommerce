package com.example.Ecommerce.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Table(name = "product")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    private double price;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties
    private Set<Category> categories;

    @OneToMany(mappedBy = "orderItems", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public Set<Category> getCategories(){
        return categories != null ? categories : Collections.emptySet();
    }
}
