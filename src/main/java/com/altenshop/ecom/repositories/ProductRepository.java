package com.altenshop.ecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altenshop.ecom.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {
}

