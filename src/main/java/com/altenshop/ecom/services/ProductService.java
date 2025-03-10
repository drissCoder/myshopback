package com.altenshop.ecom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.altenshop.ecom.entities.Product;
import com.altenshop.ecom.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(int id, Product updatedProduct) {
        return productRepository.findById(id).map(existingProduct -> {
            if (updatedProduct.getName() != null) {
                existingProduct.setName(updatedProduct.getName());
            }
            if (updatedProduct.getPrice() != 0) {
                existingProduct.setPrice(updatedProduct.getPrice());
            }
            return productRepository.save(existingProduct);
        });
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}

