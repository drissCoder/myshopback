package com.altenshop.ecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altenshop.ecom.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire
}

