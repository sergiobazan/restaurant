package com.bazan.restaurant.menus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IMenuRepository extends JpaRepository<Menu, Long> {
}
