package com.bazan.restaurant.restaurants;

import com.bazan.restaurant.menus.Dish;
import com.bazan.restaurant.menus.Menu;
import com.bazan.restaurant.orders.Order;
import com.bazan.restaurant.shared.services.ISlugService;
import com.bazan.restaurant.users.UserProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String slug;
    private String address;
    private String description;
    private LocalTime openAt;
    private LocalTime closeAt;

    @ManyToOne
    @JsonIgnore
    private UserProfile owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "restaurant_id")
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Dish> dishes = new HashSet<>();

    private Restaurant(
            String name,
            String slug,
            String address,
            String description,
            LocalTime openAt,
            LocalTime closeAt,
            UserProfile owner
    ) {
        this.name = name;
        this.slug = slug;
        this.address = address;
        this.description = description;
        this.openAt = openAt;
        this.closeAt = closeAt;
        this.owner = owner;
    }

    public static Restaurant create(
            String name,
            String address,
            String description,
            LocalTime openAt,
            LocalTime closeAt,
            UserProfile owner,
            ISlugService slugService
    ) {
        String slug = slugService.getSlug(name, String.valueOf(owner.getId()));
        return new Restaurant(name, slug, address, description, openAt, closeAt, owner);
    }
}
