package com.abmcoder.abm.repositories;

import com.abmcoder.abm.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long> {
}
