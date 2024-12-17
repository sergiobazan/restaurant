package com.bazan.restaurant.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o where o.restaurant.Id = :restaurantId")
    List<Order> getOrderByRestaurant(@Param("restaurantId") long restaurantId);
}
