package com.bazan.restaurant.restaurants;

import com.bazan.restaurant.menus.Menu;
import com.bazan.restaurant.restaurants.DTOs.RestaurantRequest;
import com.bazan.restaurant.restaurants.DTOs.RestaurantResponseDto;
import com.bazan.restaurant.users.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public RestaurantResponseDto getById(long id) throws Exception {
        var restaurant = restaurantRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Restaurant was not found"));

        Optional<Menu> todayMenu = restaurant
                .getMenus()
                .stream()
                .filter(m -> m.getDate().equals(LocalDate.now()))
                .findFirst();

        return new RestaurantResponseDto(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getDescription(),
                restaurant.getOpenAt(),
                restaurant.getCloseAt(),
                todayMenu
        );
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
