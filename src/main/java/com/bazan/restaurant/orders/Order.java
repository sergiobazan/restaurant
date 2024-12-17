package com.bazan.restaurant.orders;

import com.bazan.restaurant.menus.Menu;
import com.bazan.restaurant.restaurants.Restaurant;
import com.bazan.restaurant.users.UserProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private OrderStatus status;
    private PaymentStatus paymentStatus;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private UserProfile client;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Menu menu;

    private Order(
            UserProfile client,
            Menu menu,
            Restaurant restaurant,
            String description
    ) {
        this.client = client;
        this.menu = menu;
        this.restaurant = restaurant;
        this.description = description;
    }

    public static Order Create(
            UserProfile client,
            Menu menu,
            Restaurant restaurant,
            String description
    ) {
        Order order = new Order(client, menu, restaurant, description);
        order.setStatus(OrderStatus.PENDING);
        order.setPaymentStatus(PaymentStatus.PENDING);
        return order;
    }
}
