package ma.altenshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.altenshop.entities.Panier;
import ma.altenshop.entities.Product;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findByPanier(Panier panier);
	void deleteByPanier(Panier panier);
}
