package com.greennest.backend.service;

import com.greennest.backend.entity.Cart;

public interface CartService {

    Cart addItemToCart(Long userId, Long plantId, Integer quantity);

    Cart getCartByUserId(Long userId);
    
    Cart updateItemQuantity(Long cartItemId, Integer quantity);

    Cart removeItemFromCart(Long cartItemId);
}
