package com.bazan.restaurant.menus;

import com.bazan.restaurant.orders.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private double unitPrice;
    private DishType type;
    private boolean isAvailable;

    @ManyToMany(mappedBy = "dishes")
    @JsonIgnore
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(mappedBy = "dish")
    @JsonIgnore
    private Set<OrderItem> orderItems = new HashSet<>();

    private Dish(String name, String description, double unitPrice, DishType type, boolean isAvailable) {
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.type = type;
        this.isAvailable = isAvailable;
    }

    public static Dish create(String name, String description, double unitPrice, DishType type, boolean isAvailable) {
        return new Dish(name, description, unitPrice, type, isAvailable);
    }
}
