package com.bazan.restaurant.orders;

import com.bazan.restaurant.menus.Dish;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    private double unitPrice;

    public OrderItem(Order order, Dish dish, double unitPrice) {
        this.order = order;
        this.dish = dish;
        this.unitPrice = unitPrice;
    }
}
