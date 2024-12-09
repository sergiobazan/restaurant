package com.bazan.restaurant.restaurants;

import com.bazan.restaurant.owners.IOwnerRepository;
import com.bazan.restaurant.restaurants.DTOs.RestaurantRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService implements IRestaurantService {

    private final IRestaurantRepository restaurantRepository;
    private final IOwnerRepository ownerRepository;

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant create(RestaurantRequest restaurantRequest) throws Exception {
        var owner = ownerRepository
                .findById(restaurantRequest.ownerId())
                .orElseThrow(() -> new Exception("Owner was not found"));
        var restaurant = Restaurant.create(
                restaurantRequest.name(),
                restaurantRequest.address(),
                restaurantRequest.description(),
                restaurantRequest.openAt(),
                restaurantRequest.closeAt(),
                owner
        );
        return restaurantRepository.save(restaurant);
    }
}
