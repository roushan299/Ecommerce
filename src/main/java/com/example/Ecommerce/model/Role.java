package com.example.Ecommerce.model;

import lombok.*;

import java.util.Set;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private Long id;

    private String name;

    private Set<User> users;
}
