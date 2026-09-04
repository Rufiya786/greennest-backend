package com.greennest.backend.repository;

import com.greennest.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser_UserIdOrderByOrderDateDesc(Long userId);
}
