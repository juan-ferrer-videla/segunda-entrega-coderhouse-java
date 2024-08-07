package com.abmcoder.abm.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clients")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class Client {

    @Schema(description = "Identificador unico", example = "1")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @Schema(description = "Nombre del cliente", example = "Juan")
    @Getter @Setter private String name;

    @Schema(description = "Apellido del cliente", example = "Ferrer")
    @Getter @Setter private String lastname;

    @Schema(description = "DNI del cliente", example = "40002637")
    @Getter @Setter private int dni;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invoice> invoices;
}
