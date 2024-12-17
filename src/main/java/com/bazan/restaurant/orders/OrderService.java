package com.bazan.restaurant.orders;

import com.bazan.restaurant.menus.Dish;
import com.bazan.restaurant.menus.IDishRepository;
import com.bazan.restaurant.menus.IMenuRepository;
import com.bazan.restaurant.orders.DTOs.DishResponseDto;
import com.bazan.restaurant.orders.DTOs.OrderRequest;
import com.bazan.restaurant.orders.DTOs.OrderResponseDto;
import com.bazan.restaurant.restaurants.IRestaurantRepository;
import com.bazan.restaurant.users.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

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

    @Override
    public List<OrderResponseDto> getOrderByRestaurantId(long restaurantId, String range) {
        LocalDate start = getStartDate(range);
        LocalDate end = getEndDate(range);

        List<Order> orders = orderRepository.getOrderByRestaurant(restaurantId, start, end);
        return orders.stream()
                .map(order -> new OrderResponseDto(
                        order.getId(),
                        order.getRestaurant().getId(),
                        order.getMenu().getId(),
                        order.getClient().getId(),
                        order.getClient().getName(),
                        order.getDescription(),
                        order.getStatus(),
                        order.getPaymentStatus(),
                        order.getOrderItems().stream().map(oi -> new DishResponseDto(
                                oi.getDish().getId(),
                                oi.getDish().getName(),
                                oi.getDish().getType()
                        )).toList()
                ))
                .toList();
    }

    @Override
    public void updateStatus(long id, int status) throws Exception {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new Exception("Order not found"));
        order.setStatus(OrderStatus.fromValue(status));
        orderRepository.save(order);
    }

    @Override
    public void updatePaymentStatus(long id, int paymentStatus) throws Exception {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new Exception("Order not found"));
        order.setPaymentStatus(PaymentStatus.fromValue(paymentStatus));
        orderRepository.save(order);
    }

    private static LocalDate getStartDate(String range) {
        LocalDate today = LocalDate.now();
        return switch (range) {
            case "today" -> today;
            case "week" -> today.with(DayOfWeek.MONDAY);
            case "month" -> today.withDayOfMonth(1);
            case "all" -> LocalDate.of(1900, 1, 1);
            default -> throw new IllegalArgumentException("Invalid range: " + range);
        };
    }

    private static LocalDate getEndDate(String range) {
        LocalDate today = LocalDate.now();
        return switch (range) {
            case "today" -> today;
            case "week" -> today.with(DayOfWeek.SUNDAY);
            case "month" -> today.with(lastDayOfMonth());
            case "all" -> LocalDate.of(2050, 1, 1);
            default -> throw new IllegalArgumentException("Invalid range: " + range);
        };
    }
}
