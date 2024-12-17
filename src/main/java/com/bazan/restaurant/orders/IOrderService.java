package com.bazan.restaurant.orders;

import com.bazan.restaurant.orders.DTOs.OrderRequest;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();
    Order create(OrderRequest orderRequest) throws Exception;
}
