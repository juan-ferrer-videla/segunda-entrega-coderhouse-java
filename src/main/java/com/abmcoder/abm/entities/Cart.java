package com.abmcoder.abm.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carts")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @Getter @Setter private Integer amount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Getter @Setter private Product product;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @Getter @Setter private Client client;
}