package com.bazan.restaurant.users.DTOs;

import java.time.LocalDate;

public record UserResponseDto(long id, String name, String email, LocalDate birthDay, String role) {
}
