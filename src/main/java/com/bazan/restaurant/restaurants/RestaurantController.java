package com.bazan.restaurant.restaurants;

import com.bazan.restaurant.restaurants.DTOs.RestaurantRequest;
import com.bazan.restaurant.restaurants.DTOs.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("restaurants")
public class RestaurantController {

    private final IRestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(
            @RequestBody RestaurantRequest restaurantRequest
    ) {
        try {
            var restaurant = restaurantService.create(restaurantRequest);
            var result = RestaurantResponse.Success("Restaurant created successfully", restaurant);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            var result = RestaurantResponse.Failure(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }
}
