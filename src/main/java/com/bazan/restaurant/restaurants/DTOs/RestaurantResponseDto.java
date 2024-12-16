package com.bazan.restaurant.restaurants.DTOs;

import com.bazan.restaurant.menus.Menu;

import java.time.LocalTime;
import java.util.Optional;

public record RestaurantResponseDto(
        long id,
        String name,
        String address,
        String description,
        LocalTime openAt,
        LocalTime closeAt,
        Optional<Menu> menu
) {
}
