package com.bazan.restaurant.orders.DTOs;

import java.util.List;

public record OrderRequest(
        long clientId,
        long menuId,
        long restaurantId,
        String description,
        List<Long> dishIds) {
}
