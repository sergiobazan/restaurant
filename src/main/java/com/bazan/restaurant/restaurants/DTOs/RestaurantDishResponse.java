package com.bazan.restaurant.restaurants.DTOs;

import com.bazan.restaurant.menus.Dish;
import com.bazan.restaurant.shared.DTOs.Response;

import java.util.List;

public class RestaurantDishResponse extends Response<List<Dish>> {

    private RestaurantDishResponse(boolean success, String message, List<Dish> data) {
        super(success, message, data);
    }

    public static RestaurantDishResponse Success(String message, List<Dish> data) {
        return new RestaurantDishResponse(true, message, data);
    }

    public static RestaurantDishResponse Failure(String message) {
        return new RestaurantDishResponse(false, message, null);
    }
}
