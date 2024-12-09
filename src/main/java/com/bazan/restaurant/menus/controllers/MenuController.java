package com.bazan.restaurant.menus.controllers;

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
            var response = MenuResponse.Success("Menu created susscessfully", menu);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            var response = MenuResponse.Failure(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

    }
}
