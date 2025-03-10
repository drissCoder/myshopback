package com.altenshop.ecom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.altenshop.ecom.entities.Cart;
import com.altenshop.ecom.entities.Product;
import com.altenshop.ecom.repositories.CartRepository;
import com.altenshop.ecom.repositories.ProductRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;

    private ProductRepository productRepository;
    
    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @Transactional
    public void addProductToCart(Long cartId, Integer productId, int quantity) {
        // Trouver ou créer un nouveau panier
        Cart cart = cartRepository.findById(cartId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setId(cartId); // Vous pouvez utiliser une autre logique pour l'ID si nécessaire
            return cartRepository.save(newCart);
        });

        // Vérifier si le produit existe
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Ajouter le produit au panier
        cart.addItem(product, quantity);

        // Sauvegarder les modifications
        cartRepository.save(cart);
    }
    
    @Transactional
    public void removeProductFromCart(Long cartId, Integer productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        cartRepository.save(cart);
    }


    @Transactional
    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Vider tous les items du panier
        cart.getItems().clear();

        cartRepository.save(cart); // Sauvegarder les modifications
    }
    
    public void increaseProductQuantity(Long cartId, Integer productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(item -> item.setQuantity(item.getQuantity() + quantity));

        cartRepository.save(cart);
    }
    
    public void decreaseProductQuantity(Long cartId, Integer productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(item -> {
                    int newQuantity = item.getQuantity() - quantity;
                    if (newQuantity <= 0) {
                        cart.getItems().remove(item);
                    } else {
                        item.setQuantity(newQuantity);
                    }
                });

        cartRepository.save(cart);
    }

    
}
