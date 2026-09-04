package com.greennest.backend.service.impl;

import com.greennest.backend.entity.*;
import com.greennest.backend.exception.BadRequestException;
import com.greennest.backend.exception.ResourceNotFoundException;
import com.greennest.backend.repository.*;
import com.greennest.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                             CartRepository cartRepository,
                             AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Order placeOrder(Long userId, Long addressId) {

        Cart cart = cartRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No cart found for user id: " + userId));

        if (cart.getItems().isEmpty()) {
            throw new BadRequestException("Cannot place an order with an empty cart");
        }

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + addressId));

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setAddress(address);
        order.setStatus(OrderStatus.ORDER_PLACED);
        order.setOrderDate(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPlant(cartItem.getPlant());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceAtPurchase(cartItem.getPlant().getPrice());

            order.getItems().add(orderItem);

            BigDecimal lineTotal = cartItem.getPlant().getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            total = total.add(lineTotal);
        }

        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);

        cart.getItems().clear();
        cartRepository.save(cart);

        return savedOrder;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUser_UserIdOrderByOrderDateDesc(userId);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
    }

    @Override
    public Order updateOrderStatus(Long orderId, OrderStatus newStatus) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

        order.setStatus(newStatus);

        return orderRepository.save(order);
    }
}