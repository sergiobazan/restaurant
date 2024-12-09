package com.bazan.restaurant.menus.DTOs;

import java.time.LocalDate;

public record MenuRequest(String name, LocalDate date, double price, long restaurantId) {
}
