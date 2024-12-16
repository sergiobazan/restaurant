package com.bazan.restaurant.users.DTOs;

import com.bazan.restaurant.restaurants.DTOs.RestaurantResponseDto;
import com.bazan.restaurant.shared.DTOs.Response;

public class OwnerResponse extends Response<RestaurantResponseDto> {

    private OwnerResponse(boolean success, String message, RestaurantResponseDto data) {
        super(success, message, data);
    }

    public static OwnerResponse Success(String message, RestaurantResponseDto data) {
        return new OwnerResponse(true, message, data);
    }

    public static OwnerResponse Failure(String message) {
        return new OwnerResponse(false, message, null);
    }
}
