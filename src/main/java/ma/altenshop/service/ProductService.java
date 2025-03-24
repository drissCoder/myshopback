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
	
	public Product getProduct(Integer id) throws Exception {
		return productRepository.findById(id)
				.orElseThrow(() -> new Exception("Product does not exists"));
	}
	
	public Product updateProduct(Product product, Integer id) throws Exception{
		Product updatedroduct = getProduct(id);
		product.setId(updatedroduct.getId());
		return productRepository.save(product);
	}
	
	public void deleteProduct(Integer id) throws Exception {
		Product p = getProduct(id);
		
		productRepository.deleteById(p.getId());
	}
	
	
}
