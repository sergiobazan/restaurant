package com.bazan.restaurant.users;

import com.bazan.restaurant.restaurants.DTOs.RestaurantResponseDto;
import com.bazan.restaurant.users.DTOs.LoginRequest;
import com.bazan.restaurant.users.DTOs.UserRequest;
import com.bazan.restaurant.users.DTOs.UserResponseDto;

import java.util.List;

public interface IUserService {
    List<UserProfile> getAll();
    UserResponseDto create(UserRequest userProfile);
    String login(LoginRequest loginRequest) throws Exception;
    UserResponseDto getUser(String auth) throws Exception;
    RestaurantResponseDto getRestaurantByOwnerId(long  id) throws Exception;
}
