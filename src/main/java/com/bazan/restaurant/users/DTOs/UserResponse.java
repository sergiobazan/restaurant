package com.bazan.restaurant.users.DTOs;

import com.bazan.restaurant.shared.DTOs.Response;

public class UserResponse extends Response<UserResponseDto> {

    private UserResponse(boolean success, String message, UserResponseDto data) {
        super(success, message, data);
    }

    public static UserResponse Success(String message, UserResponseDto data) {
        return new UserResponse(true, message, data);
    }

    public static UserResponse Failure(String message) {
        return new UserResponse(false, message, null);
    }
}
