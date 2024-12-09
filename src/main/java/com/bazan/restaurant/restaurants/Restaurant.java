package com.bazan.restaurant.restaurants;

import com.bazan.restaurant.menus.Menu;
import com.bazan.restaurant.owners.Owner;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Menu> menus = new HashSet<>();

    private Restaurant(
            String name,
            String address,
            String description,
            LocalDateTime openAt,
            LocalDateTime closeAt,
            Owner owner
    ) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.openAt = openAt;
        this.closeAt = closeAt;
        this.owner = owner;
    }

    public static Restaurant create(
            String name,
            String address,
            String description,
            LocalDateTime openAt,
            LocalDateTime closeAt,
            Owner owner
    ) {
        return new Restaurant(name, address, description, openAt, closeAt, owner);
    }
}
