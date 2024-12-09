package com.bazan.restaurant.restaurants.DTOs;

import com.bazan.restaurant.restaurants.Restaurant;
import com.bazan.restaurant.shared.DTOs.Response;

public class RestaurantResponse extends Response<Restaurant> {

    private RestaurantResponse(boolean success, String message, Restaurant data) {
        super(success, message, data);
    }

    public static RestaurantResponse Success(String message, Restaurant data) {
        return new RestaurantResponse(true, message, data);
    }

    public static RestaurantResponse Failure(String message) {
        return new RestaurantResponse(false, message, null);
    }
}
