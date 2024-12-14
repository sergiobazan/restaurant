package com.bazan.restaurant.menus;

import com.bazan.restaurant.restaurants.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "menus")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private LocalDate date;
    private double price;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "menu_dish",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> dishes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    private Menu(String name, LocalDate date, double price, Restaurant restaurant) {
        this.name = name;
        this.date = date;
        this.price = price;
        this.restaurant = restaurant;
    }

    public static Menu create(String name, LocalDate date, double price, Restaurant restaurant) {
        return new Menu(name, date, price, restaurant);
    }
}
