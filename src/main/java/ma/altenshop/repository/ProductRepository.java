package ma.altenshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.altenshop.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	
}
