package com.bazan.restaurant.restaurants;

import com.bazan.restaurant.restaurants.DTOs.RestaurantRequest;
import com.bazan.restaurant.users.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService implements IRestaurantService {

    private final IRestaurantRepository restaurantRepository;
    private final IUserRepository userRepository;

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant create(RestaurantRequest restaurantRequest) throws Exception {
        var owner = userRepository
                .findById(restaurantRequest.ownerId())
                .orElseThrow(() -> new Exception("Owner was not found"));

        if (!owner.getRole().equals("OWNER"))
            throw new Exception("Only an Owner can create a restaurant");

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
