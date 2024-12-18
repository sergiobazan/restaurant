package com.bazan.restaurant.users.DTOs;

import com.bazan.restaurant.orders.DTOs.DishResponseDto;
import com.bazan.restaurant.orders.OrderStatus;
import com.bazan.restaurant.orders.PaymentStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ClientOrderDto(
        long id,
        String name,
        String restaurantName,
        String description,
        OrderStatus status,
        PaymentStatus paymentStatus,
        LocalDateTime createdAt,
        List<DishResponseDto> dishes
) {
}
