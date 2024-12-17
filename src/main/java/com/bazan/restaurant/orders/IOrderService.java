package com.bazan.restaurant.orders;

import com.bazan.restaurant.orders.DTOs.OrderRequest;
import com.bazan.restaurant.orders.DTOs.OrderResponseDto;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();
    Order create(OrderRequest orderRequest) throws Exception;
    List<OrderResponseDto> getOrderByRestaurantId(long restaurantId);
    void updateStatus(long id, int status) throws Exception;
    void updatePaymentStatus(long id, int paymentStatus) throws Exception;
}
