package com.greennest.backend.dto;

import java.math.BigDecimal;

public class DashboardStatsResponse {

    private long totalPlants;
    private long totalUsers;
    private long totalOrders;
    private BigDecimal totalSales;

    public DashboardStatsResponse(long totalPlants, long totalUsers, long totalOrders, BigDecimal totalSales) {
        this.totalPlants = totalPlants;
        this.totalUsers = totalUsers;
        this.totalOrders = totalOrders;
        this.totalSales = totalSales;
    }

    public long getTotalPlants() {
        return totalPlants;
    }

    public void setTotalPlants(long totalPlants) {
        this.totalPlants = totalPlants;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }
}
