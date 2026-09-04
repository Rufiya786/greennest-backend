package com.greennest.backend.repository;

import com.greennest.backend.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCart_CartIdAndPlant_PlantId(Long cartId, Long plantId);
}