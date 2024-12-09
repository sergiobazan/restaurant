package com.bazan.restaurant.menus;

import com.bazan.restaurant.menus.DTOs.MenuRequest;

import java.util.List;

public interface IMenuService {
    List<Menu> getAll();
    Menu create(MenuRequest menuRequest) throws Exception;
    void addDish(long menuId, long dishId) throws Exception;
}
