package com.bazan.restaurant.users.DTOs;

import com.bazan.restaurant.shared.DTOs.Response;
import com.bazan.restaurant.users.UserProfile;

public class UserResponse extends Response<UserProfile> {

    private UserResponse(boolean success, String message, UserProfile data) {
        super(success, message, data);
    }

    public static UserResponse Success(String message, UserProfile data) {
        return new UserResponse(true, message, data);
    }

    public static UserResponse Failure(String message) {
        return new UserResponse(false, message, null);
    }
}
