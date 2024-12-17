package com.bazan.restaurant.orders.DTOs;

import com.bazan.restaurant.orders.Order;
import com.bazan.restaurant.shared.DTOs.Response;

public class OrderResponse extends Response<Order> {

    private OrderResponse(boolean success, String message, Order data) {
        super(success, message, data);
    }

    public static OrderResponse Success(String message, Order data) {
        return new OrderResponse(true, message, data);
    }

    public static OrderResponse Failure(String message) {
        return new OrderResponse(false, message, null);
    }
}
