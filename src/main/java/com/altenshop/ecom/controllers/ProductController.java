package com.altenshop.ecom.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altenshop.ecom.entities.Product;
import com.altenshop.ecom.services.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET: Retrieve all products
    @GetMapping("/products")
    public List<Product> listProducts() {
        return productService.getAllProducts();
    }

    // POST: Create a new product
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(201).body(createdProduct);
    }

    // GET: Retrieve details for a specific product
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).build());
    }

    // PATCH: Update a specific product
    @PatchMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).build());
    }

    // DELETE: Delete a specific product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        if (productService.getProductById(id).isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}