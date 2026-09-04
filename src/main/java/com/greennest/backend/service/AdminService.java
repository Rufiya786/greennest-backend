package com.greennest.backend.service;

import com.greennest.backend.dto.AdminLoginRequest;
import com.greennest.backend.dto.DashboardStatsResponse;
import com.greennest.backend.entity.Admin;
import com.greennest.backend.entity.Order;
import com.greennest.backend.entity.User;

import java.util.List;

public interface AdminService {

    Admin loginAdmin(AdminLoginRequest request);

    DashboardStatsResponse getDashboardStats();

    List<User> getAllUsers();

    List<Order> getAllOrders();
}