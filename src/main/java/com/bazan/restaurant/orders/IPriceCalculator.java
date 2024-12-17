package com.bazan.restaurant.orders;

import java.util.List;

public interface IPriceCalculator {
    double calculatePrice(List<OrderItem> orderItems);
}
