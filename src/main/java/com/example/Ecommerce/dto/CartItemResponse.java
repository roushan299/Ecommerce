package com.example.Ecommerce.dto;


import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItemResponse {

    private Long cartId;
    private Long userId;
    private Long productId;
    private Long quantity;
    private BigDecimal price;
    private ProductDto productDto;
}
