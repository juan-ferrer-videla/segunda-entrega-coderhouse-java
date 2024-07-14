package com.abmcoder.abm.controllers;

import com.abmcoder.abm.entities.Cart;
import com.abmcoder.abm.services.CartsService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/carts")
public class CartsController {

    private static class UpdateProductRequest {
       @Getter @Setter private Long clientId;
       @Getter @Setter private Long productId;
    }

    @Autowired
    private CartsService service;

    @PostMapping()
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart) {
        try {
            return new ResponseEntity<>(service.addToCart(cart), HttpStatus.CREATED);
        } catch( Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Cart>>  readProducts() {
        try {
            return ResponseEntity.ok(service.readCarts());
        } catch (Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public List<Cart> readCartsByClientId(@PathVariable("id") long id) {
        try {
            return service.readCartsByClientId(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("READ ONE ERROR");
        }
    }

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
