package com.greennest.backend.controller;

import com.greennest.backend.dto.AdminLoginRequest;
import com.greennest.backend.dto.DashboardStatsResponse;
import com.greennest.backend.entity.Admin;
import com.greennest.backend.entity.Order;
import com.greennest.backend.entity.User;
import com.greennest.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestBody AdminLoginRequest request) {
        Admin admin = adminService.loginAdmin(request);
        return ResponseEntity.ok(admin);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardStatsResponse> getDashboard() {
        DashboardStatsResponse stats = adminService.getDashboardStats();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = adminService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}