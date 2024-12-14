package com.bazan.restaurant.users;

import com.bazan.restaurant.restaurants.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_profile")
public class UserProfile implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String password;
    private LocalDate birthDay;
    private String role;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private Set<Restaurant> restaurants = new HashSet<>();

    private UserProfile(String name, String email, String password, LocalDate birthDay, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDay = birthDay;
        this.role = role;
    }

    public static UserProfile Create(String name, String email, String password, LocalDate birthDay, String role) {
        return new UserProfile(name, email, password, birthDay, role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("USER"),
                new SimpleGrantedAuthority(this.role)
        );
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
