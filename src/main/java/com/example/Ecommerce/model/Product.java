package com.example.Ecommerce.model;


import jakarta.persistence.*;
import lombok.*;

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
}
