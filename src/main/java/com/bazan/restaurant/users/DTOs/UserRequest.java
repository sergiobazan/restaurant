package com.bazan.restaurant.users.DTOs;

import java.time.LocalDate;

public record UserRequest(String name, String email, String password, LocalDate birthDay, String role) {
}
