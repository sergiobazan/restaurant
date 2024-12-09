package com.bazan.restaurant.menus.controllers;

import com.bazan.restaurant.menus.DTOs.DishRequest;
import com.bazan.restaurant.menus.DTOs.DishResponse;
import com.bazan.restaurant.menus.Dish;
import com.bazan.restaurant.menus.IDishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("dishes")
public class DishController {

    private final IDishService dishService;

    @GetMapping
    public ResponseEntity<List<Dish>> getAllDishes(){
        return ResponseEntity.ok(dishService.getAll());
    }

    @PostMapping
    public ResponseEntity<DishResponse> createDish(
            @RequestBody DishRequest dishRequest
    ) {
        try {
            var dish = dishService.create(dishRequest);
            var result = DishResponse.Success("Dish created successfully", dish);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            var result = DishResponse.Failure(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }
}
