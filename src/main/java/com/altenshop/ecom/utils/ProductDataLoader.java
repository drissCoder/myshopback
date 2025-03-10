package com.altenshop.ecom.utils;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.altenshop.ecom.entities.Product;
import com.altenshop.ecom.enums.InventoryStatus;
import com.altenshop.ecom.repositories.ProductRepository;

@Component
public class ProductDataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    public ProductDataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        List<Product> products = List.of(
            new Product(
                "P001", "Laptop", "High-performance laptop", "/images/laptop.png",
                "Electronics", 999.99, 10, "REF001", 101, InventoryStatus.INSTOCK,
                5
            ),
            new Product(
                "P002", "Smartphone", "Latest smartphone", "/images/smartphone.png",
                "Electronics", 699.99, 25, "REF002", 102, InventoryStatus.INSTOCK,
                4
            ),
            new Product(
                "P003", "Tablet", "Lightweight tablet", "/images/tablet.png",
                "Electronics", 399.99, 15, "REF003", 103, InventoryStatus.LOWSTOCK,
                4
            ),
            new Product(
                "P004", "Smartwatch", "Feature-rich smartwatch", "/images/smartwatch.png",
                "Wearables", 199.99, 50, "REF004", 104, InventoryStatus.INSTOCK,
                5
            )
        );

        productRepository.saveAll(products);
    }
}
