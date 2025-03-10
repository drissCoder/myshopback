package com.altenshop.ecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altenshop.ecom.entities.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire
}

