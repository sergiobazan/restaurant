package com.bazan.restaurant.restaurants;

import com.bazan.restaurant.menus.Menu;
import com.bazan.restaurant.owners.Owner;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String address;
    private String description;
    private LocalDateTime openAt;
    private LocalDateTime closeAt;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "restaurant")
    private HashSet<Menu> menus = new HashSet<>();
}
