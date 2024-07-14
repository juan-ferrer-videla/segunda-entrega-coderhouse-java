package com.abmcoder.abm.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @Getter @Setter private String name;

    @Getter @Setter private int stock;

    @Getter @Setter private double price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts;
}
