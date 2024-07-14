package com.abmcoder.abm.services;

import com.abmcoder.abm.entities.Client;
import com.abmcoder.abm.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsService {

    @Autowired
    private ClientsRepository repository;

    public Client saveClient(Client client) {
       return repository.save(client);
    }

    public List<Client> readClients() {
        return repository.findAll();
    }

    public Optional<Client> readOneClient(Long id) {
        return repository.findById(id);
    }

    public void destroyOneClient(Long id) {
        repository.deleteById(id);
    }
}
