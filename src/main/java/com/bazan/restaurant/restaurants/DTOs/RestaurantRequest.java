package com.bazan.restaurant.restaurants.DTOs;

import java.time.LocalTime;

public record RestaurantRequest(
        String name,
        String address,
        String description,
        LocalTime openAt,
        LocalTime closeAt,
        long ownerId
) {
}
