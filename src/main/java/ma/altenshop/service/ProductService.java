package ma.altenshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ma.altenshop.entities.Product;
import ma.altenshop.repository.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
	}

	public Product createProduct(Product product) {
		
		return productRepository.save(product);
	}
	
	public Product getProduct(Integer id) {
		return productRepository.findById(id).get();
	}
	
	public Product updateProduct(Product product, Integer id){
		Product updatedroduct = getProduct(id);
		
		return productRepository.save(updatedroduct);
	}
	
	public void deleteProduct(Integer id) {
		
		productRepository.deleteById(id);
	}
	
	
}
