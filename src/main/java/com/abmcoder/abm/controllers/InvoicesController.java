package com.abmcoder.abm.controllers;

import com.abmcoder.abm.entities.Invoice;
import com.abmcoder.abm.services.InvoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/invoices")
public class InvoicesController {

    @Autowired
    private InvoicesService service;

    @PostMapping()
    public ResponseEntity<Invoice> saveInvoice(@RequestBody Long id) {
        try {
            return new ResponseEntity<>(service.saveInvoice(id), HttpStatus.CREATED);
        } catch( Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Invoice>> readInvoices() {
        try {
            return ResponseEntity.ok(service.readInvoices());
        } catch (Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Invoice>> read(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(service.readInvoicesByClientId(id));
        } catch (Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Invoice>> readByClientId(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(service.readInvoicesByClientId(id));
        } catch (Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
