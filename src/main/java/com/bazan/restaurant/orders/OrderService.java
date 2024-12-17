package com.bazan.restaurant.orders;

import com.bazan.restaurant.menus.IMenuRepository;
import com.bazan.restaurant.orders.DTOs.OrderRequest;
import com.bazan.restaurant.restaurants.IRestaurantRepository;
import com.bazan.restaurant.users.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;
    private final IMenuRepository menuRepository;
    private final IRestaurantRepository restaurantRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order create(OrderRequest orderRequest) throws Exception {
        var client = this.userRepository
                .findById(orderRequest.clientId())
                .orElseThrow(() -> new Exception("Client not found"));
        var menu = this.menuRepository
                .findById(orderRequest.menuId())
                .orElseThrow(() -> new Exception("Menu not found"));
        var restaurant = this.restaurantRepository
                .findById(orderRequest.restaurantId())
                .orElseThrow(() -> new Exception("Restaurant not found"));

        var order = Order.Create(
                client,
                menu,
                restaurant,
                orderRequest.description()
        );
        return orderRepository.save(order);
    }
}
