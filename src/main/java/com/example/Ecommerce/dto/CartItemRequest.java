package com.example.Ecommerce.dto;


import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItemRequest {

    private Long cartId;

    private Long userId;

    private Long productId;

    private Long quantity;

}
