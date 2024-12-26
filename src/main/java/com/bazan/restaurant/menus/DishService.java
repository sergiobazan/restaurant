package com.bazan.restaurant.menus;

import com.bazan.restaurant.menus.DTOs.DishRequest;
import com.bazan.restaurant.restaurants.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DishService implements IDishService {

    private final IDishRepository dishRepository;
    private final IRestaurantRepository restaurantRepository;

    @Override
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    @Override
    public Dish create(DishRequest dishRequest) throws Exception {
        var restaurant = restaurantRepository
                .findById(dishRequest.restaurantId())
                .orElseThrow(() -> new Exception("Restaurant was not found"));

        var dish = Dish.create(
                dishRequest.name(),
                dishRequest.description(),
                dishRequest.unitPrice(),
                dishRequest.type(),
                dishRequest.isAvailable(),
                restaurant
        );
        return dishRepository.save(dish);
    }
}
