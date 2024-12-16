package com.bazan.restaurant.restaurants;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r Where r.owner.id = :ownerId")
    Optional<Restaurant> findByOwnerId(@Param("ownerId") long ownerId);
}
