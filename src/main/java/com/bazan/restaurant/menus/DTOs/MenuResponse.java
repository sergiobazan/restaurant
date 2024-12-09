package com.bazan.restaurant.menus.DTOs;

import com.bazan.restaurant.menus.Menu;
import com.bazan.restaurant.shared.DTOs.Response;

public class MenuResponse extends Response<Menu> {

    private MenuResponse(boolean success, String message, Menu data) {
        super(success, message, data);
    }

    public static MenuResponse Success(String message, Menu data) {
        return new MenuResponse(true, message, data);
    }

    public static MenuResponse Failure(String message) {
        return new MenuResponse(false, message, null);
    }
}
