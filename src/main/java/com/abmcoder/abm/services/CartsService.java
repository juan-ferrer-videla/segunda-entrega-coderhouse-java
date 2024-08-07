package com.abmcoder.abm.services;


import com.abmcoder.abm.entities.Cart;
import com.abmcoder.abm.entities.Client;
import com.abmcoder.abm.entities.Product;
import com.abmcoder.abm.repositories.CartsRepository;
import com.abmcoder.abm.repositories.ClientsRepository;
import com.abmcoder.abm.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartsService {

    @Autowired
    private CartsRepository repository;

    @Autowired
    private ClientsRepository clientRepository;

    @Autowired
    private ProductsRepository productRepository;

    public List<Cart> readCarts() {
        return repository.findUnpaidCarts();
    }

    public List<Cart> readCartsByClientId(Long clientId) {
        return repository.findByClientId(clientId);
    }

    public void destroyOneCart(Long id) {
        repository.deleteById(id);
    }

    public void deleteProductFromCart(Long clientId, Long productId) {
        repository.decrementProductAmount(clientId, productId);
        repository.deleteEmptyProduct(clientId, productId);
    }

    public Cart addToCart(Cart cart) throws Exception {
        Long productId = cart.getProduct().getId();
        Long clientId = cart.getClient().getId();

        Optional<Product> optionalProduct = productRepository.findById(productId);
        Optional<Client> optionalClient = clientRepository.findById(clientId);

        if (optionalProduct.isPresent() && optionalClient.isPresent()) {
            Product product = optionalProduct.get();
            Client client = optionalClient.get();

            cart.setProduct(product);
            cart.setClient(client);

            List<Cart> carts = repository.findByClientIdAndProductName(cart.getClient().getId(), cart.getProduct().getName());

            Integer stock = product.getStock();

            if (carts.size() > 0) {
                Cart cartToUpdate = carts.get(0);
                if (stock < cartToUpdate.getAmount() + cart.getAmount()) {
                    throw new Exception("No existen " + (cartToUpdate.getAmount() + cart.getAmount()) + " unidades en stock");
                }
                cartToUpdate.setAmount(cartToUpdate.getAmount() + cart.getAmount());
                return repository.save(cartToUpdate);
            } else {
                if (stock < cart.getAmount()) {
                    throw new Exception("No existen " + cart.getAmount() + " unidades en stock");
                }
                return repository.save(cart);
            }
        } else {
            throw new RuntimeException("No se encontró el cliente o producto asociado.");
        }
    }
}