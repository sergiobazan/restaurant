package com.bazan.restaurant.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Long> {

    @Query("""
            SELECT o FROM Order o WHERE o.restaurant.Id = :restaurantId
            AND DATE(o.createdAt) BETWEEN :start AND :end
            """)
    List<Order> getOrderByRestaurant(
            @Param("restaurantId") long restaurantId,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end);
}
