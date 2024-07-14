package com.abmcoder.abm.services;

import com.abmcoder.abm.entities.Product;
import com.abmcoder.abm.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> readProducts() {
        return repository.findAll();
    }

    public Optional<Product> readOneProduct(Long id) {
        return repository.findById(id);
    }

    public void destroyOneProduct(Long id) {
        repository.deleteById(id);
    }

    public void updateProduct(Long id, Product updatedClient) {
        Optional<Product> optionalClient = repository.findById(id);
        if (optionalClient.isPresent()) {
            Product existingProduct = optionalClient.get();
            existingProduct.setName(updatedClient.getName());
            existingProduct.setPrice(updatedClient.getPrice());
            existingProduct.setStock(updatedClient.getStock());
            repository.save(existingProduct);
        } else {
            throw new RuntimeException("El producto con el id: " + id + " no fue encontrado.");
        }
    }

}
