package com.bazan.restaurant.menus.DTOs;

import com.bazan.restaurant.menus.DishType;

public record DishRequest(String name, String description, double unitPrice, DishType type, boolean isAvailable) {}
