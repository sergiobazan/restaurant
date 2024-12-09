package com.bazan.restaurant.menus;

import com.bazan.restaurant.menus.DTOs.MenuRequest;
import com.bazan.restaurant.restaurants.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuService implements IMenuService {

    private final IMenuRepository menuRepository;
    private final IRestaurantRepository restaurantRepository;

    @Override
    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu create(MenuRequest menuRequest) throws Exception {
        var restaurant = restaurantRepository
                .findById(menuRequest.restaurantId())
                .orElseThrow(() -> new Exception("Restaurant with given Id not found"));

        var menu = Menu.create(
                menuRequest.name(),
                menuRequest.date(),
                menuRequest.price(),
                restaurant
        );
        return menuRepository.save(menu);
    }
}
