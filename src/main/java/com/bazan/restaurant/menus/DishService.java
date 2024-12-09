package com.bazan.restaurant.menus;

import com.bazan.restaurant.menus.DTOs.DishRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DishService implements IDishService {

    private final IDishRepository dishRepository;

    @Override
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    @Override
    public Dish create(DishRequest dishRequest) {

        var dish = Dish.create(
                dishRequest.name(),
                dishRequest.description(),
                dishRequest.unitPrice()
        );
        return dishRepository.save(dish);
    }
}
