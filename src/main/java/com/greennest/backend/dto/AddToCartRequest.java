package com.greennest.backend.dto;

public class AddToCartRequest {

    private Long userId;
    private Long plantId;
    private Integer quantity;

    public AddToCartRequest() {
        // needed so Jackson can create this object from JSON
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
