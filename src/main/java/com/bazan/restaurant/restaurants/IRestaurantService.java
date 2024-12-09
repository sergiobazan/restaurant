package com.bazan.restaurant.restaurants;

import com.bazan.restaurant.restaurants.DTOs.RestaurantRequest;

import java.util.List;

public interface IRestaurantService {
    List<Restaurant> getAll();
    Restaurant create(RestaurantRequest restaurantRequest) throws Exception;
}
