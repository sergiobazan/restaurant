package com.bazan.restaurant.shared.services;

import com.bazan.restaurant.users.UserProfile;
import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {
    String generateToken(UserProfile user);
    String getUserEmailFromToken(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
