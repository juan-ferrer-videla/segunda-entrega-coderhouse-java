package com.abmcoder.abm.controllers;

import com.abmcoder.abm.entities.Client;
import com.abmcoder.abm.services.ClientsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/auth")
@Tag(name = "Rutas del cliente", description = "CRUD clientes")
public class ClientsController {

    @Autowired
    private ClientsService service;

    @PostMapping("/register")
    @Operation(summary = "Crea un nuevo cliente")
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        try {
            return new ResponseEntity<>(service.saveClient(client), HttpStatus.CREATED);
        } catch( Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtiene todos los clientes")
    @GetMapping()
    public ResponseEntity<List<Client>> readClients() {
        try {
            return ResponseEntity.ok(service.readClients());
        } catch (Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtiene un cliente")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Client>> readOneClient(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(service.readOneClient(id));
        } catch (Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Borra un cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Client>  destroyOneClient(@PathVariable("id") long id) {
        try {
            service.destroyOneClient(id);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Edita un cliente")
    @PatchMapping("/me/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client data) {
        try {
            Optional<Client> optionalClient = service.readOneClient(id);
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                if (data.getName() != null) {
                    client.setName(data.getName());
                }
                if (data.getLastname() != null) {
                    client.setLastname(data.getLastname());
                }
                if (data.getDni() != 0) {
                    client.setDni(client.getDni());
                }
                return ResponseEntity.ok(service.saveClient(client));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
