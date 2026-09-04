package com.greennest.backend.service;

import com.greennest.backend.entity.Order;
import com.greennest.backend.entity.OrderStatus;

import java.util.List;

public interface OrderService {

    Order placeOrder(Long userId, Long addressId);

    List<Order> getOrdersByUserId(Long userId);

    Order getOrderById(Long orderId);

    Order updateOrderStatus(Long orderId, OrderStatus newStatus);
}
