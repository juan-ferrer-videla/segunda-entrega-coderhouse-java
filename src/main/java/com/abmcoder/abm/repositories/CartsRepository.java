package com.abmcoder.abm.repositories;

import com.abmcoder.abm.entities.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartsRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.client.id = :clientId AND c.payed = false")
    List<Cart> findByClientId(@Param("clientId") Long clientId);

    @Query("SELECT c FROM Cart c WHERE c.payed = false")
    List<Cart> findUnpaidCarts();

    @Query("SELECT c FROM Cart c WHERE c.client.id = :clientId AND c.product.name = :productName AND c.payed = false")
    List<Cart> findByClientIdAndProductName(@Param("clientId") Long clientId, @Param("productName") String productName);


    @Modifying
    @Transactional
    @Query("UPDATE Cart c SET c.amount = c.amount - 1 WHERE c.client.id = :clientId AND c.product.id = :productId AND c.amount > 0 AND c.payed = false")
    void decrementProductAmount(Long clientId, Long productId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.client.id = :clientId AND c.payed = false")
    void deleteCartsByClientId(Long clientId);


    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.client.id = :clientId AND c.product.id = :productId AND c.amount = 0")
    void deleteEmptyProduct(Long clientId, Long productId);

    // You can also pass parameters to the query
    @Query("SELECT c FROM Cart c WHERE c.client.id = :clientId AND c.payed = false")
    List<Cart> findUnpaidCartsByClient(@Param("clientId") Long clientId);

}
