package com.bazan.restaurant.menus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface IMenuRepository extends JpaRepository<Menu, Long> {

    @Query("SELECT COUNT(m) > 0 FROM Menu m WHERE m.restaurant.id = :restaurantId AND m.date = :date")
    boolean existsMenuInRestaurant(
            @Param("restaurantId") long restaurantId,
            @Param("date") LocalDate date);
}
