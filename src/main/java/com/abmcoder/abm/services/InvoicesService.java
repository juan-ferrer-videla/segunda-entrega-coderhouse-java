package com.abmcoder.abm.services;


import com.abmcoder.abm.entities.Cart;
import com.abmcoder.abm.entities.Client;
import com.abmcoder.abm.entities.Invoice;
import com.abmcoder.abm.repositories.CartsRepository;
import com.abmcoder.abm.repositories.ClientsRepository;
import com.abmcoder.abm.repositories.InvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InvoicesService {

    @Autowired
    private InvoicesRepository repository;

    @Autowired
    private CartsRepository cartsRepository;

    @Autowired
    private ClientsRepository clientsRepository;

    public Invoice saveInvoice(Long clientId) throws Exception {
        Optional<Client> client = clientsRepository.findById(clientId);

        if (!client.isPresent()) {
            throw new Exception("No se encuentra el cliente");
        }

        Invoice invoice = new Invoice();
        List<Cart> carts = cartsRepository.findByClientId(clientId);
        double total = 0;

        for (Cart cart : carts) {
            total += cart.getAmount() * cart.getProduct().getPrice();
        }

        invoice.setTotal(total);
        invoice.setClient(client.get());
        invoice.setCreated_at(LocalDateTime.now());

        return repository.save(invoice);
    }

    public List<Invoice> readInvoices() {
        return repository.findAll();
    }

    public Optional<Invoice> readOneInvoice(Long id) {
        return repository.findById(id);
    }

    public List<Invoice> readInvoicesByClientId(Long id) {
        return repository.findByClientId(id);
    }

    public void destroyOneInvoice(Long id) {
        repository.deleteById(id);
    }

}