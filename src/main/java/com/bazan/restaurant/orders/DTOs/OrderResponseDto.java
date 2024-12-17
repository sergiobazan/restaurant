package com.bazan.restaurant.orders.DTOs;

import com.bazan.restaurant.orders.OrderStatus;
import com.bazan.restaurant.orders.PaymentStatus;

import java.util.List;

public record OrderResponseDto(
        long id,
        long restaurantId,
        long menuId,
        long clientId,
        String clientName,
        String description,
        OrderStatus status,
        PaymentStatus paymentStatus,
        List<DishResponseDto> dishes
) {
}
