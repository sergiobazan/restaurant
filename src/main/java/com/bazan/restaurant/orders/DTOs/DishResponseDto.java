package com.bazan.restaurant.orders.DTOs;

import com.bazan.restaurant.menus.DishType;

public record DishResponseDto(long id, String name, DishType type) {
}
