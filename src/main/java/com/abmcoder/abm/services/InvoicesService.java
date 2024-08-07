package com.abmcoder.abm.services;


import com.abmcoder.abm.entities.Cart;
import com.abmcoder.abm.entities.Client;
import com.abmcoder.abm.entities.Invoice;
import com.abmcoder.abm.entities.Product;
import com.abmcoder.abm.repositories.CartsRepository;
import com.abmcoder.abm.repositories.ClientsRepository;
import com.abmcoder.abm.repositories.InvoicesRepository;
import com.abmcoder.abm.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class InvoicesService {

    @Autowired
    private InvoicesRepository repository;

    @Autowired
    private CartsRepository cartsRepository;

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private ProductsRepository productsRepository;


    public Invoice saveInvoice(Long clientId) throws Exception {
        Optional<Client> client = clientsRepository.findById(clientId);

        if (!client.isPresent()) {
            throw new Exception("No se encuentra el cliente");
        }

        Invoice invoice = new Invoice();
        List<Cart> carts = cartsRepository.findUnpaidCartsByClient(clientId);
        double total = 0;

        for (Cart cart : carts) {
            total += cart.getAmount() * cart.getProduct().getPrice();
            cart.setPayed(true);
            Optional<Product> product = productsRepository.findById(cart.getProduct().getId());
            if (product.isPresent()) {
                Product currentProduct = product.get();
                currentProduct.setStock(currentProduct.getStock() - cart.getAmount());
                productsRepository.save(currentProduct);
            }

            cartsRepository.save(cart);
        }

        invoice.setTotal(total);
        if (total == 0) {
            throw new Exception("No existen productos en el carrito");
        }
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

    public Invoice readLastInvoiceByClientId(Long id) throws Exception {
        List<Invoice> invoices = repository.findByClientId(id);
        try {
            return invoices.get(invoices.size() - 1);
        } catch (Exception e) {
            throw new Exception("invoice not found");
        }
    }

    public void destroyOneInvoice(Long id) {
        repository.deleteById(id);
    }

}