package com.abmcoder.abm.controllers;

import com.abmcoder.abm.entities.Invoice;
import com.abmcoder.abm.services.InvoicesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/invoices")
@Tag(name = "Rutas de la factura", description = "CRUD factura")
public class InvoicesController {

    @Autowired
    private InvoicesService service;

    @Operation(summary = "Crea una factura", description = "Crea una factura para los carritos activos del cliente, tambien descuenta los productos del stock")
    @PostMapping()
    public ResponseEntity<Invoice> saveInvoice(@RequestBody Long id) {
        try {
            return new ResponseEntity<>(service.saveInvoice(id), HttpStatus.CREATED);
        } catch( Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtiene todas las facturas")
    @GetMapping()
    public ResponseEntity<List<Invoice>> readInvoices() {
        try {
            return ResponseEntity.ok(service.readInvoices());
        } catch (Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtiene las facturas de un cliente")
    @GetMapping("/{id}")
    public ResponseEntity<List<Invoice>> readInvoicesByClientId(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(service.readInvoicesByClientId(id));
        } catch (Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtiene la ultima factura de un cliente")
    @GetMapping("/last/{id}")
    public ResponseEntity<Invoice> readLastInvoiceByClientId(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(service.readLastInvoiceByClientId(id));
        } catch (Exception exception) {
            System.out.println(exception);
            return ResponseEntity.notFound().build();
        }
    }
}
