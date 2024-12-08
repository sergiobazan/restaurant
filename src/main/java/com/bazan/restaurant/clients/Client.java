package com.bazan.restaurant.clients;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(unique = true)
    private String email;
    private LocalDate birthDay;

    private Client(String name, String email, LocalDate birthDay) {
        this.name = name;
        this.email = email;
        this.birthDay = birthDay;
    }

    public static Client create(String name, String email, LocalDate birthDay) {
        return new Client(name, email, birthDay);
    }
}
