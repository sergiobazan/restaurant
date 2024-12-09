package com.bazan.restaurant.menus;

import com.bazan.restaurant.menus.DTOs.DishRequest;

import java.util.List;

public interface IDishService {
    List<Dish> getAll();
    Dish create(DishRequest dishRequest);
}

