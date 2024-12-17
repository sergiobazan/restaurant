package com.bazan.restaurant.orders.DTOs;

public record OrderRequest(
        long clientId,
        long menuId,
        long restaurantId,
        String description) {
}
