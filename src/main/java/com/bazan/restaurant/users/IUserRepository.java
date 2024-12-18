package com.bazan.restaurant.users;

import com.bazan.restaurant.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IUserRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByEmail(String email);

    @Query("""
            SELECT o FROM Order o WHERE o.client.id = :clientId AND
            DATE(o.createdAt) BETWEEN :start AND :end
            """)
    List<Order> getOrdersByClientId(
            @Param("clientId") long clientId,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end);
}
