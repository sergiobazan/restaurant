package com.bazan.restaurant.menus.controllers;

import com.bazan.restaurant.menus.DTOs.MenuDish;
import com.bazan.restaurant.menus.DTOs.MenuRequest;
import com.bazan.restaurant.menus.DTOs.MenuResponse;
import com.bazan.restaurant.menus.IMenuService;
import com.bazan.restaurant.menus.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("menus")
public class MenuController {
    private final IMenuService menuService;

    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenus() {
        return ResponseEntity.ok(menuService.getAll());
    }

    @PostMapping
    public ResponseEntity<MenuResponse> createMenu(
            @RequestBody MenuRequest menuRequest
    ) {
        try {
            var menu = menuService.create(menuRequest);
            var response = MenuResponse.Success("Menu created successfully", menu);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            var response = MenuResponse.Failure(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

    }

    @PostMapping("{id}/dish")
    public ResponseEntity<?> addDishToMenu(
            @PathVariable long  id,
            @RequestBody MenuDish menuDish
    ) {
        try {
            menuService.addDish(id, menuDish.dishId());
            var response = MenuResponse.Success("Dish added to Menu successfully", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            var response = MenuResponse.Failure(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
