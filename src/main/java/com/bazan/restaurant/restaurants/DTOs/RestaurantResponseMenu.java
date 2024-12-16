package com.bazan.restaurant.restaurants.DTOs;

import com.bazan.restaurant.shared.DTOs.Response;

public class RestaurantResponseMenu extends Response<RestaurantResponseDto> {

    private RestaurantResponseMenu(boolean success, String message, RestaurantResponseDto data) {
        super(success, message, data);
    }

    public static RestaurantResponseMenu Success(String message, RestaurantResponseDto data) {
        return new RestaurantResponseMenu(true, message, data);
    }

    public static RestaurantResponseMenu Failure(String message) {
        return new RestaurantResponseMenu(false, message, null);
    }
}
