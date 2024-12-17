package com.bazan.restaurant.orders;

import com.bazan.restaurant.menus.Dish;
import com.bazan.restaurant.menus.IDishRepository;
import com.bazan.restaurant.menus.IMenuRepository;
import com.bazan.restaurant.orders.DTOs.OrderRequest;
import com.bazan.restaurant.restaurants.IRestaurantRepository;
import com.bazan.restaurant.users.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;
    private final IMenuRepository menuRepository;
    private final IRestaurantRepository restaurantRepository;
    private final IPriceCalculator priceCalculator;
    private final IOrderItemRepository orderItemRepository;
    private final IDishRepository dishRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Transactional
    @Override
    public Order create(OrderRequest orderRequest) throws Exception {
        var client = userRepository
                .findById(orderRequest.clientId())
                .orElseThrow(() -> new Exception("Client not found"));
        var menu = menuRepository
                .findById(orderRequest.menuId())
                .orElseThrow(() -> new Exception("Menu not found"));
        var restaurant = restaurantRepository
                .findById(orderRequest.restaurantId())
                .orElseThrow(() -> new Exception("Restaurant not found"));

        var order = Order.Create(
                client,
                menu,
                restaurant,
                orderRequest.description()
        );

        Order orderSaved = orderRepository.save(order);

        List<Dish> dishes = dishRepository.findAllById(orderRequest.dishIds());

        List<OrderItem> orderItems = dishes.stream()
                .map(dish -> new OrderItem(orderSaved, dish, dish.getUnitPrice()))
                .toList();

        List<OrderItem> orderItemList = orderItemRepository.saveAll(orderItems);

        orderSaved.setOrderItems(new HashSet<>(orderItemList));

        order.setTotalPrice(priceCalculator.calculatePrice(orderItemList));

        return orderSaved;
    }
}
