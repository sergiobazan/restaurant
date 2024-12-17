package com.bazan.restaurant.orders.DTOs;

import com.bazan.restaurant.shared.DTOs.Response;

import java.util.List;

public class OrderRestaurantResponse extends Response<List<OrderResponseDto>> {

    private OrderRestaurantResponse(boolean success, String message, List<OrderResponseDto> data) {
        super(success, message, data);
    }

    public static OrderRestaurantResponse Success(String message, List<OrderResponseDto> data) {
        return new OrderRestaurantResponse(true, message, data);
    }

    public static OrderRestaurantResponse Failure(String message) {
        return new OrderRestaurantResponse(false, message, null);
    }
}
