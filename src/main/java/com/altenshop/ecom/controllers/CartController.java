package com.altenshop.ecom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.altenshop.ecom.entities.Cart;
import com.altenshop.ecom.services.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Ajouter un produit au panier
    @PostMapping("/{cartId}/add-product")
    public ResponseEntity<?> addProductToCart(
            @PathVariable Long cartId,
            @RequestParam Integer productId,
            @RequestParam int quantity) {
        cartService.addProductToCart(cartId, productId, quantity);
        return ResponseEntity.ok("Product added to cart");
    }

    // Supprimer un produit du panier
    @DeleteMapping("/{cartId}/remove-product")
    public ResponseEntity<?> removeProductFromCart(
            @PathVariable Long cartId,
            @RequestParam Integer productId) {
        cartService.removeProductFromCart(cartId, productId);
        return ResponseEntity.ok("Product removed from cart");
    }

    // Augmenter la quantité d'un produit
    @PatchMapping("/{cartId}/increase-quantity")
    public ResponseEntity<?> increaseProductQuantity(
            @PathVariable Long cartId,
            @RequestParam Integer productId,
            @RequestParam int quantity) {
        cartService.increaseProductQuantity(cartId, productId, quantity);
        return ResponseEntity.ok("Product quantity increased");
    }

    // Diminuer la quantité d'un produit
    @PatchMapping("/{cartId}/decrease-quantity")
    public ResponseEntity<?> decreaseProductQuantity(
            @PathVariable Long cartId,
            @RequestParam Integer productId,
            @RequestParam int quantity) {
        cartService.decreaseProductQuantity(cartId, productId, quantity);
        return ResponseEntity.ok("Product quantity decreased");
    }

    // Vider le panier
    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<?> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.ok("Cart cleared");
    }

    // Obtenir les détails du panier
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId) {
        Cart cart = cartService.getCart(cartId);
        return ResponseEntity.ok(cart);
    }
}
