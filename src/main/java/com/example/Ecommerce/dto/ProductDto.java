package com.example.Ecommerce.dto;


import lombok.*;

@Data
@Builder
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String name;
    private double price;
    private String description;
}
