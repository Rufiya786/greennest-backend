package com.greennest.backend.service.impl;

import com.greennest.backend.entity.Cart;
import com.greennest.backend.entity.CartItem;
import com.greennest.backend.entity.Plant;
import com.greennest.backend.entity.User;
import com.greennest.backend.exception.BadRequestException;
import com.greennest.backend.exception.ResourceNotFoundException;
import com.greennest.backend.repository.CartItemRepository;
import com.greennest.backend.repository.CartRepository;
import com.greennest.backend.repository.PlantRepository;
import com.greennest.backend.repository.UserRepository;
import com.greennest.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final PlantRepository plantRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository,
                            CartItemRepository cartItemRepository,
                            UserRepository userRepository,
                            PlantRepository plantRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.plantRepository = plantRepository;
    }

    @Override
    public Cart addItemToCart(Long userId, Long plantId, Integer quantity) {

        Cart cart = getOrCreateCart(userId);

        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Plant not found with id: " + plantId));

        Optional<CartItem> existingItem =
                cartItemRepository.findByCart_CartIdAndPlant_PlantId(cart.getCartId(), plantId);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setPlant(plant);
            newItem.setQuantity(quantity);
            cartItemRepository.save(newItem);
        }

        return cartRepository.findById(cart.getCartId()).get();
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return getOrCreateCart(userId);
    }

    @Override
    public Cart updateItemQuantity(Long cartItemId, Integer quantity) {

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + cartItemId));

        if (quantity <= 0) {
            throw new BadRequestException("Quantity must be greater than zero. Use remove instead.");
        }

        item.setQuantity(quantity);
        cartItemRepository.save(item);

        return cartRepository.findById(item.getCart().getCartId()).get();
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId) {

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + cartItemId));

        Long cartId = item.getCart().getCartId();

        cartItemRepository.deleteById(cartItemId);

        return cartRepository.findById(cartId).get();
    }

    // ---------- helper method ----------
    private Cart getOrCreateCart(Long userId) {

        Optional<Cart> existingCart = cartRepository.findByUser_UserId(userId);

        if (existingCart.isPresent()) {
            return existingCart.get();
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Cart newCart = new Cart();
        newCart.setUser(user);

        return cartRepository.save(newCart);
    }
}