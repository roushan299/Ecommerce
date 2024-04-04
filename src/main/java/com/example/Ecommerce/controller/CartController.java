package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.CartItemRequest;
import com.example.Ecommerce.exceptions.NoCartExitsException;
import com.example.Ecommerce.model.Cart;
import com.example.Ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Object> addToCart(@RequestBody CartItemRequest cartItemRequest, @RequestHeader("userId") Long userId){
        cartItemRequest.setUserId(userId);
        ResponseEntity<Object> response = cartService.createNewCart(cartItemRequest);
        return response;
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity getCartById(@PathVariable("userId") Long userId){
        Cart cart = null;
        try {
            cart = cartService.getCartById(userId);
        } catch (NoCartExitsException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<Object> updateCartWithCartItem(@PathVariable Long userId, @RequestBody CartItemRequest cartItemRequest){
        ResponseEntity<Object> response = cartService.updateCartWithCartItem(userId, cartItemRequest);
        return response;
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Object> deleteCartItemFromCart(@PathVariable Long userId,@RequestHeader Long cartItemId){
        ResponseEntity<Object> response = cartService.deleteCartItemFromCart(userId, cartItemId);
        return response;
    }

}
