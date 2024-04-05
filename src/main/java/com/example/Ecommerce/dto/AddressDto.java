package com.example.Ecommerce.dto;


import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private String city;

    private String street;

    private  String firstName;

    private String lastName;

    private Long userId;
}
