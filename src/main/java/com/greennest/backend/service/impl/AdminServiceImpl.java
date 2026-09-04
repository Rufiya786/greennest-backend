package com.greennest.backend.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greennest.backend.dto.AdminLoginRequest;
import com.greennest.backend.dto.DashboardStatsResponse;
import com.greennest.backend.entity.Admin;
import com.greennest.backend.entity.Order;
import com.greennest.backend.entity.User;
import com.greennest.backend.exception.UnauthorizedException;
import com.greennest.backend.repository.AdminRepository;
import com.greennest.backend.repository.OrderRepository;
import com.greennest.backend.repository.PlantRepository;
import com.greennest.backend.repository.UserRepository;
import com.greennest.backend.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final PlantRepository plantRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository,
                             PasswordEncoder passwordEncoder,
                             PlantRepository plantRepository,
                             UserRepository userRepository,
                             OrderRepository orderRepository) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.plantRepository = plantRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Admin loginAdmin(AdminLoginRequest request) {

        Admin admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));

        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), admin.getPassword());

        if (!passwordMatches) {
            throw new UnauthorizedException("Invalid email or password");
        }

        return admin;
    }

    @Override
    public DashboardStatsResponse getDashboardStats() {

        long totalPlants = plantRepository.count();
        long totalUsers = userRepository.count();

        List<Order> allOrders = orderRepository.findAll();
        long totalOrders = allOrders.size();

        BigDecimal totalSales = BigDecimal.ZERO;
        for (Order order : allOrders) {
            totalSales = totalSales.add(order.getTotalAmount());
        }

        return new DashboardStatsResponse(totalPlants, totalUsers, totalOrders, totalSales);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}