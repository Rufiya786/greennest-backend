package com.greennest.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greennest.backend.dto.AddToCartRequest;
import com.greennest.backend.entity.Cart;
import com.greennest.backend.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Cart> addToCart(@RequestBody AddToCartRequest request) {
        Cart cart = cartService.addItemToCart(
                request.getUserId(),
                request.getPlantId(),
                request.getQuantity()
        );
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }
    
    @PutMapping("/{cartItemId}")
    public ResponseEntity<Cart> updateQuantity(@PathVariable Long cartItemId,
                                                @RequestParam Integer quantity) {
        Cart cart = cartService.updateItemQuantity(cartItemId, quantity);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Cart> removeItem(@PathVariable Long cartItemId) {
        Cart cart = cartService.removeItemFromCart(cartItemId);
        return ResponseEntity.ok(cart);
    }

}
