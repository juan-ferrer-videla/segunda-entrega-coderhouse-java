package com.abmcoder.abm.controllers;

import com.abmcoder.abm.entities.Cart;
import com.abmcoder.abm.services.CartsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/carts")
@Tag(name = "Rutas de carrito", description = "CRUD carrito")
public class CartsController {

    private static class UpdateProductRequest {
       @Getter @Setter private Long clientId;
       @Getter @Setter private Long productId;
    }

    @Autowired
    private CartsService service;

    @PostMapping()
    @Operation(summary = "Añade un producto al carrito", description = "En el caso de que no exista el producto en el carrito se crea uno nuevo," +
            "en el caso que ya exista se le suma la nueva cantidad a comprar a la ya existente")
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart) {
        try {
            return new ResponseEntity<>(service.addToCart(cart), HttpStatus.CREATED);
        } catch( Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping()
    @Operation(summary = "Obtiene todos los carritos", description = "Obtiene los productos de los carritos impagos, es decir, que todavía estan activos")
    public ResponseEntity<List<Cart>>  readProducts() {
        try {
            return ResponseEntity.ok(service.readCarts());
        } catch (Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene los productos del carrito del cliente", description = "Obtiene los productos del carrito impago de un cliente en especifico")
    public List<Cart> readCartsByClientId(@PathVariable("id") long id) {
        try {
            return service.readCartsByClientId(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("READ ONE ERROR");
        }
    }

    @Operation(summary = "Elimina los productos del carrito del cliente", description = "Elimina los productos del carrito impagos de un cliente en especifico")
    @DeleteMapping("/{id}")
    public void destroyOneProduct(@PathVariable("id") long id) {
        try {
            service.destroyOneCart(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("DELETE ONE ERROR");
        }
    }

    @PutMapping()
    @Operation(summary = "Descuenta un producto del carrito", description = "Descuenta una unidad del producto del carrito del cliente")
    public ResponseEntity<String> updateOneProduct(@RequestBody UpdateProductRequest request) {
        try {
            service.deleteProductFromCart(request.getClientId(), request.getProductId());
            return ResponseEntity.ok("Product amount updated successfully");
        } catch (Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
