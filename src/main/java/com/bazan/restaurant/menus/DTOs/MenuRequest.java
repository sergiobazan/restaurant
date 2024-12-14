package com.bazan.restaurant.menus.DTOs;

import java.time.LocalDate;
import java.util.List;

public record MenuRequest(String name, LocalDate date, double price, long restaurantId, List<Long> dishes) {
}
