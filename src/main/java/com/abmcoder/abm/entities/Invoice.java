package com.abmcoder.abm.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class Invoice {

    @Schema(description = "Identificador unico", example = "1")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @Schema(description = "Total a pagar en la factura", example = "16000")
    @Getter @Setter private double total;

    @Schema(description = "Fecha de emisión de la factura", example = "2024-08-05T19:22:45.7184")
    @Getter @Setter private LocalDateTime created_at;

    @Schema(description = "Identificador al ID del cliente")
    @ManyToOne
    @JoinColumn(name = "client_id")
    @Getter @Setter private Client client;
}
