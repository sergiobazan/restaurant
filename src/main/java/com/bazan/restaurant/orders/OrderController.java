package com.bazan.restaurant.orders;

import com.bazan.restaurant.orders.DTOs.OrderRequest;
import com.bazan.restaurant.orders.DTOs.OrderResponse;
import com.bazan.restaurant.orders.DTOs.OrderRestaurantResponse;
import com.bazan.restaurant.shared.DTOs.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("orders")
public class OrderController {

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

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<OrderRestaurantResponse> getOrderByRestaurant(
            @PathVariable("id") long id
    ) {
        try {
            var orders = orderService.getOrderByRestaurantId(id);
            var result = OrderRestaurantResponse.Success("Success", orders);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            var result = OrderRestaurantResponse.Failure(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PutMapping("/{id}/status/{statusId}")
    public ResponseEntity<Response<String>> updateStatus(
            @PathVariable("id") long id,
            @PathVariable("statusId") int statusId
    ) {
        try {
            orderService.updateStatus(id, statusId);
            return ResponseEntity.ok(new Response<String>(true, "Success", "Status updated"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response<String>(false, "Failure", e.getMessage()));
        }
    }

    @PutMapping("/{id}/payment-status/{statusId}")
    public ResponseEntity<Response<String>> updatePaymentStatus(
            @PathVariable("id") long id,
            @PathVariable("statusId") int statusId
    ) {
        try {
            orderService.updatePaymentStatus(id, statusId);
            return ResponseEntity.ok(new Response<String>(true, "Success", "Status updated"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response<String>(false, "Failure", e.getMessage()));
        }
    }
}
