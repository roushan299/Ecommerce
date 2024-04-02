package com.example.Ecommerce.service;


import com.example.Ecommerce.dto.CartItemRequest;
import com.example.Ecommerce.exceptions.NoCartExitsException;
import com.example.Ecommerce.exceptions.ProductNotExitsException;
import com.example.Ecommerce.model.Cart;
import com.example.Ecommerce.model.CartItem;
import com.example.Ecommerce.model.Product;
import com.example.Ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemService cartItemService;


    public ResponseEntity<Object> createNewCart(CartItemRequest cartItemRequest) {
        ResponseEntity<Object> response = null;
        Long userId = cartItemRequest.getUserId();
        try {
            //check if cart exits or not
            Cart cart = getCartById(userId);
            cartItemRequest.setCartId(cart.getId());
        } catch (NoCartExitsException e) {
            // if not create cart and then add cartItem to the cart
            List<CartItem> items = new ArrayList<>();
            BigDecimal totalPrice = new BigDecimal(0);

            Cart cart = Cart.builder()
                    .userId(userId)
                    .totalPrice(totalPrice)
                    .items(items)
                    .build();

            cart = cartRepository.save(cart);
            cartItemRequest.setCartId(cart.getId());
        }
        response = updateCartWithCartItem(userId, cartItemRequest);
        return  response;
    }

    public Cart getCartById(Long id) throws NoCartExitsException {
        Optional<Cart> optionalCart = cartRepository.findByUserId(id);
        if(optionalCart.isEmpty()){
            throw new NoCartExitsException("No such cart exits");
        }
        Cart cart = optionalCart.get();
        return cart;
    }

    public ResponseEntity<Object> updateCartWithCartItem(Long userId, CartItemRequest cartItemRequest){
        ResponseEntity<Object> response = null;
        try {
            Cart cart = getCartById(userId);
            List<CartItem> cartItemList = cart.getItems();
            CartItem cartItem = cartItemService.addCartItem(cart, cartItemRequest);
            cartItemList.add(cartItem);
            BigDecimal totalPrice = cart.getTotalPrice().add(cartItem.getPrice());
            cart.setTotalPrice(totalPrice);
            cartRepository.save(cart);
            response = new ResponseEntity<>("Item is added to the cart successfully", HttpStatus.OK);
            return response;
        } catch (NoCartExitsException e) {
            response = createNewCart(cartItemRequest);
            return response;
        } catch (ProductNotExitsException e) {
            // if no such product exits in the system
            throw new RuntimeException(e);
        }
    }


    public ResponseEntity<Object> deleteCartItemFromCart(Long userId, Long cartItemId){
        ResponseEntity<Object> response = null;
        try {
            Cart cart = getCartById(userId);
            List<CartItem> cartItemList = cart.getItems();

            for(CartItem cartItem: cartItemList){
                if(cartItem.getId()==cartItemId){
                    CartItem exitingCartItem = cartItemService.deleteCartItemById(cartItemId);
                    boolean isCartItemDeletedFromCart = cartItemList.remove(cartItem);
                    if(isCartItemDeletedFromCart){
                        BigDecimal totalPrice = cart.getTotalPrice().subtract(exitingCartItem.getPrice());
                        cart.setTotalPrice(totalPrice);
                    }
                }
            }
            cartRepository.save(cart);
            response = new ResponseEntity<>("Item is deleted from the cart", HttpStatus.OK);
            return response;
        } catch (NoCartExitsException e) {
            throw new RuntimeException(e);
        }
    }
}
