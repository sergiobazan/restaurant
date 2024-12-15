package com.bazan.restaurant.restaurants;

import com.bazan.restaurant.restaurants.DTOs.RestaurantRequest;
import com.bazan.restaurant.restaurants.DTOs.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public ResponseEntity<RestaurantResponse> getById(
            @PathVariable long id
    ) {
        try {
            var restaurant = restaurantService.getById(id);
            var result = RestaurantResponse.Success("Success", restaurant);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            var result = RestaurantResponse.Failure(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }
}
