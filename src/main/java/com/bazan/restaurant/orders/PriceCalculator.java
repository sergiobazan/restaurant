package com.bazan.restaurant.orders;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculator implements IPriceCalculator {

    @Override
    public double calculatePrice(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToDouble(OrderItem::getUnitPrice)
                .sum();
    }
}
