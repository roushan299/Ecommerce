package com.example.Ecommerce.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Collections;
import java.util.Set;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;

    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles != null ? roles : Collections.emptySet();
    }



}
