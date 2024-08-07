package com.abmcoder.abm.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carts")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Schema(description = "Representa un producto del carrito")
public class Cart {
    @Schema(description = "Identificador unico", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @Schema(description = "Cantidad de unidades del producto existentes en el carrito del cliente", example = "1")
    @Getter @Setter private Integer amount;

    @Schema(description = "Estado del carrito, una vez generada la factura su estado pasara a true y se descontara el stock del producto", example = "false")
    @Getter @Setter private Boolean payed = false;

    @Schema(description = "Identificador del producto a añadir")
    @ManyToOne
    @JoinColumn(name = "product_id")
    @Getter @Setter private Product product;

    @Schema(description = "Identificador del cliente al que pertenece el carrito")
    @ManyToOne
    @JoinColumn(name = "client_id")
    @Getter @Setter private Client client;
}