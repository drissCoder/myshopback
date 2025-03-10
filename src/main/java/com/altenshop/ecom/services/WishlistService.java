package com.altenshop.ecom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.altenshop.ecom.entities.Product;
import com.altenshop.ecom.entities.User;
import com.altenshop.ecom.entities.Wishlist;
import com.altenshop.ecom.repositories.UserRepository;
import com.altenshop.ecom.repositories.WishlistRepository;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository, UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
    }

    
    // Créer une wishlist pour un utilisateur
    @Transactional
    public Wishlist createWishlist(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        return wishlistRepository.save(wishlist);
    }
    
    // Ajouter un produit à la wishlist
    @Transactional
    public void addProductToWishlist(Long wishlistId, Product product) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElse(null);
        if (wishlist != null) {
            wishlist.getProducts().add(product);
            wishlistRepository.save(wishlist);
        }
    }

    // Retirer un produit de la wishlist
    public void removeProductFromWishlist(Long wishlistId, Product product) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElse(null);
        if (wishlist != null) {
            wishlist.getProducts().remove(product);
            wishlistRepository.save(wishlist);
        }
    }

    public Wishlist getWishlist(Long wishlistId) {
        return wishlistRepository.findById(wishlistId).orElse(null);
    }

    public void clearWishlist(Long wishlistId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElse(null);
        if (wishlist != null) {
            wishlist.getProducts().clear();
            wishlistRepository.save(wishlist);
        }
    }
}