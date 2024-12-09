package com.bazan.restaurant.menus.DTOs;

import com.bazan.restaurant.menus.Dish;
import com.bazan.restaurant.shared.DTOs.Response;

public class DishResponse extends Response<Dish> {

    public DishResponse(boolean success, String message, Dish data) {
        super(success, message, data);
    }

    public static DishResponse Success(String message, Dish dish) {
        return new DishResponse(true, message, dish);
    }

    public static DishResponse Failure(String message) {
        return new DishResponse(false, message, null);
    }
}
