package com.bazan.restaurant.owners.DTOs;

import java.time.LocalDate;

public record OwnerRequest(String name, String email, LocalDate birthDate) {
}
