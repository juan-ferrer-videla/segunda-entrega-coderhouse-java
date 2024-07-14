package com.abmcoder.abm.repositories;

import com.abmcoder.abm.entities.Cart;
import com.abmcoder.abm.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoice, Long> {
    @Query("SELECT i FROM Invoice i WHERE i.client.id = :clientId")
    List<Invoice> findByClientId(@Param("clientId") Long clientId);
}
