package com.bazan.restaurant.orders;

import com.bazan.restaurant.orders.DTOs.OrderRequest;
import com.bazan.restaurant.orders.DTOs.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("orders")
public class OrderRepository {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> create(
            @RequestBody OrderRequest orderRequest
    ) {
        try {
            var order = this.orderService.create(orderRequest);
            var result = OrderResponse.Success("Order created successfully", order);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            var result = OrderResponse.Failure(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAll() {
        return ResponseEntity.ok(this.orderService.getAll());
    }
}
