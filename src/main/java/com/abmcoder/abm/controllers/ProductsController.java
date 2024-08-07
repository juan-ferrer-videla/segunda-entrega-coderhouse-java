package com.abmcoder.abm.controllers;

import com.abmcoder.abm.entities.Product;
import com.abmcoder.abm.services.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/products")
@Tag(name = "Rutas del producto", description = "CRUD productos")
public class ProductsController {

    @Autowired
    private ProductsService service;

    @PostMapping()
    @Operation(summary = "Crea un producto")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        try {
            return new ResponseEntity<>(service.saveProduct(product), HttpStatus.CREATED);
        } catch( Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtiene todos los productos")
    @GetMapping()
    public ResponseEntity<List<Product>> readProducts() {
        try {
            return ResponseEntity.ok(service.readProducts());
        } catch (Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtiene un producto")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> readOneProduct(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(service.readOneProduct(id));
        } catch (Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Elimina un producto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> destroyOneProduct(@PathVariable("id") long id) {
        try {
            service.destroyOneProduct(id);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("DELETE ONE ERROR");
        }
    }

    @Operation(summary = "Modifica un producto")
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product data) {
        try {
            Optional<Product> optionalProduct = service.readOneProduct(id);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                if (data.getName() != null) {
                    product.setName(data.getName());
                }
                if (data.getStock() != 0) {
                    product.setStock(data.getStock());
                }
                if (data.getPrice() != 0) {
                    product.setPrice(data.getPrice());
                }
                return ResponseEntity.ok(service.saveProduct(product));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
