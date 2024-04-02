package com.example.Ecommerce.service;


import com.example.Ecommerce.dto.CartItemRequest;
import com.example.Ecommerce.exceptions.NoCartItemExitsException;
import com.example.Ecommerce.exceptions.ProductNotExitsException;
import com.example.Ecommerce.model.Cart;
import com.example.Ecommerce.model.CartItem;
import com.example.Ecommerce.model.Product;
import com.example.Ecommerce.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductService productService;


    public CartItem addCartItem(Cart cart , CartItemRequest cartItemRequest) throws ProductNotExitsException {

            Product product = productService.getProductByIdForInternal(cartItemRequest.getProductId());
            BigDecimal price = BigDecimal.valueOf(product.getPrice() * cartItemRequest.getQuantity());

            CartItem cartItem = CartItem.builder()
                    .product(product)
                    .price(price)
                    .cart(cart)
                    .build();

            cartItem = cartItemRepository.save(cartItem);
            return cartItem;
    }

    public CartItem getCartItemById(Long cartItemId) throws NoCartItemExitsException {
        Optional<CartItem> exitingCartItem = cartItemRepository.findById(cartItemId);
        if(exitingCartItem.isEmpty()){
            throw new NoCartItemExitsException("No cart item exits");
        }
        CartItem cartItem = exitingCartItem.get();
        return cartItem;
    }

    public CartItem deleteCartItemById(Long cartItemId){
        try {
            CartItem cartItem = getCartItemById(cartItemId);
            cartItemRepository.delete(cartItem);
            return cartItem;
        } catch (NoCartItemExitsException e) {
            throw new RuntimeException(e);
        }
    }
}
