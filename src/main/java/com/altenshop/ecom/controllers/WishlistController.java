package com.altenshop.ecom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altenshop.ecom.entities.Product;
import com.altenshop.ecom.entities.Wishlist;
import com.altenshop.ecom.services.WishlistService;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    // Créer une nouvelle liste d'envies pour un utilisateur
    @PostMapping("/create")
    public Wishlist createWishlist(@RequestParam Integer userId) {
        return wishlistService.createWishlist(userId);  // Associe la wishlist à un utilisateur avec userId
    }

    // Ajouter un produit à la liste d'envies
    @PostMapping("/add/{wishlistId}")
    public void addProductToWishlist(@PathVariable Long wishlistId, @RequestBody Product product) {
        wishlistService.addProductToWishlist(wishlistId, product);
    }

    // Récupérer la wishlist
    @GetMapping("/{wishlistId}")
    public Wishlist getWishlist(@PathVariable Long wishlistId) {
        return wishlistService.getWishlist(wishlistId);
    }

    // Retirer un produit de la wishlist
    @PostMapping("/remove/{wishlistId}")
    public void removeProductFromWishlist(@PathVariable Long wishlistId, @RequestBody Product product) {
        wishlistService.removeProductFromWishlist(wishlistId, product);
    }

    // Vider la wishlist
    @PostMapping("/clear/{wishlistId}")
    public void clearWishlist(@PathVariable Long wishlistId) {
        wishlistService.clearWishlist(wishlistId);
    }
}