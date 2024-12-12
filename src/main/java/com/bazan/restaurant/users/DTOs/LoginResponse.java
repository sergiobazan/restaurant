package com.bazan.restaurant.users.DTOs;

import com.bazan.restaurant.shared.DTOs.Response;

public class LoginResponse extends Response<String> {

    private LoginResponse(boolean success, String message, String data) {
        super(success, message, data);
    }

    public static LoginResponse Success(String token) {
        return new LoginResponse(true, "Login success", token);
    }

    public static LoginResponse Failure(String message) {
        return new LoginResponse(false, message, null);
    }
}
