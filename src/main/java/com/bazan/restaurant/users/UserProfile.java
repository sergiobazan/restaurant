package com.bazan.restaurant.users;

import com.bazan.restaurant.restaurants.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private LocalDate birthDay;
    private String role;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private Set<Restaurant> restaurants = new HashSet<>();

    private UserProfile(String name, String email, LocalDate birthDay, String role) {
        this.name = name;
        this.email = email;
        this.birthDay = birthDay;
        this.role = role;
    }

    public static UserProfile Create(String name, String email, LocalDate birthDay, String role) {
        return new UserProfile(name, email, birthDay, role);
    }
}
