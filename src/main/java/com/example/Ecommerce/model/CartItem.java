package com.example.Ecommerce.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cartItem")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Product product;

    private int quantity;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
//                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
//                ", cart=" + cart +
                '}';
    }
}
