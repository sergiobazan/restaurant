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
    private final IDishRepository dishRepository;

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

        var dishes = dishRepository.findAllById(menuRequest.dishes());

        if (dishes.isEmpty()) {
            throw new Exception("No dishes found for the given IDs");
        }

        menu.getDishes().addAll(dishes);

        return menuRepository.save(menu);
    }

    @Override
    public void addDish(long menuId, long dishId) throws Exception {
        var dish = dishRepository
                .findById(dishId)
                .orElseThrow(() -> new Exception("Dish with given Id not found"));
        var menu = menuRepository
                .findById(menuId)
                .orElseThrow(() -> new Exception("Menu with given Id not found"));
        
        menu.getDishes().add(dish);
        dish.getMenus().add(menu);
        menuRepository.save(menu);
    }
}
