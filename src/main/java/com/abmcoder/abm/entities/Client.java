package com.abmcoder.abm.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clients")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @Getter @Setter private String name;

    @Getter @Setter private String lastname;

    @Getter @Setter private int dni;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invoice> invoices;
}
