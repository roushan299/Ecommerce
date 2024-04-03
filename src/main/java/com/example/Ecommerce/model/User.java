package com.example.Ecommerce.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Collections;
import java.util.List;
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

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Set<Role> getRoles() {
        return roles != null ? roles : Collections.emptySet();
    }



}
