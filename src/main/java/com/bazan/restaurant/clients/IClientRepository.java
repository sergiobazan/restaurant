package com.bazan.restaurant.clients;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Long> {
}
