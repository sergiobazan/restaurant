package com.bazan.restaurant.restaurants.DTOs;

import java.time.LocalDateTime;

public record RestaurantRequest(
        String name,
        String address,
        String description,
        LocalDateTime openAt,
        LocalDateTime closeAt,
        long ownerId
) {
}
