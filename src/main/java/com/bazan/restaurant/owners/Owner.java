package com.bazan.restaurant.owners;

import com.bazan.restaurant.restaurants.Restaurant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private LocalDate birthDay;

    @OneToOne(mappedBy = "owner")
    private Restaurant restaurant;
}
