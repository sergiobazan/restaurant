package com.bazan.restaurant.users;

import com.bazan.restaurant.users.DTOs.LoginRequest;
import com.bazan.restaurant.users.DTOs.UserRequest;

import java.util.List;

public interface IUserService {
    List<UserProfile> getAll();
    UserProfile create(UserRequest userProfile);
    String login(LoginRequest loginRequest) throws Exception;
}
