package com.bazan.restaurant.restaurants;

import com.bazan.restaurant.menus.Dish;
import com.bazan.restaurant.restaurants.DTOs.RestaurantRequest;
import com.bazan.restaurant.restaurants.DTOs.RestaurantResponseDto;

import java.util.List;

public interface IRestaurantService {
    List<Restaurant> getAll();
    RestaurantResponseDto getById(long id) throws Exception;
    Restaurant create(RestaurantRequest restaurantRequest) throws Exception;
    List<Dish> getAllDishes(long id) throws Exception;
}
