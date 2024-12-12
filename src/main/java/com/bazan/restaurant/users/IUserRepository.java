package com.bazan.restaurant.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserProfile, Long> {
}
