package com.abmcoder.abm.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador unico", example = "1")
    @Getter @Setter private long id;

    @Schema(description = "Nombre del producto", example = "Remera")
    @Getter @Setter private String name;

    @Schema(description = "Stock del producto", example = "100")
    @Getter @Setter private int stock;

    @Schema(description = "Precio del producto", example = "5000")
    @Getter @Setter private double price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts;
}
