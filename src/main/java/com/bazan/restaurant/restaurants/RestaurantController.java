package com.bazan.restaurant.restaurants;

import com.bazan.restaurant.restaurants.DTOs.RestaurantDishResponse;
import com.bazan.restaurant.restaurants.DTOs.RestaurantRequest;
import com.bazan.restaurant.restaurants.DTOs.RestaurantResponse;
import com.bazan.restaurant.restaurants.DTOs.RestaurantResponseMenu;
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

    @GetMapping("/slug/{slug}")
    public ResponseEntity<RestaurantResponseMenu> getRestaurantBySlug(
            @PathVariable("slug") String slug
    ) {
        try {
            var restaurant = restaurantService.getBySlug(slug);
            var result = RestaurantResponseMenu.Success("Success", restaurant);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            var result = RestaurantResponseMenu.Failure(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<RestaurantResponseMenu> getById(
            @PathVariable long id
    ) {
        try {
            var restaurant = restaurantService.getById(id);
            var result = RestaurantResponseMenu.Success("Success", restaurant);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            var result = RestaurantResponseMenu.Failure(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("{id}/dishes")
    public ResponseEntity<RestaurantDishResponse> getAllDishes(
            @PathVariable("id") long id
    ){
        try {
            var dishes = restaurantService.getAllDishes(id);
            var result = RestaurantDishResponse.Success("Success", dishes);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            var result = RestaurantDishResponse.Failure(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }

    }
}
