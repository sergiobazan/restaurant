package com.bazan.restaurant.users.DTOs;

import com.bazan.restaurant.shared.DTOs.Response;

import java.util.List;

public class ClientOrderResponse extends Response<List<ClientOrderDto>> {

    private ClientOrderResponse(boolean success, String message, List<ClientOrderDto> data) {
        super(success, message, data);
    }

    public static ClientOrderResponse Success(String message, List<ClientOrderDto> data) {
        return new ClientOrderResponse(true, message, data);
    }

    public static ClientOrderResponse Failure(String message) {
        return new ClientOrderResponse(false, message, null);
    }
}
