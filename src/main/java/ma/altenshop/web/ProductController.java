package ma.altenshop.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.altenshop.entities.Product;
import ma.altenshop.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public ResponseEntity<?> getAllProducts() {
		try {
			return ResponseEntity.ok(productService.getAllProducts());
		}catch(Exception e) {
			ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
			return ResponseEntity.of(problemDetail).build();
		}
		
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<?> getProduct(@PathVariable Integer id) {
		try {
		return ResponseEntity.ok(productService.getProduct(id));
	}catch(Exception e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
		return ResponseEntity.of(problemDetail).build();
	}
	}
	
	@PostMapping("/products")
	public ResponseEntity<?> createProduct(@RequestBody Product product) {
		try {
		return ResponseEntity.ok(productService.createProduct(product));
	}catch(Exception e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
		return ResponseEntity.of(problemDetail).build();
	}
	}
	
	@PatchMapping("/products/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
		try {
			return ResponseEntity.ok(productService.updateProduct(product, id));
		}catch(Exception e) {
			ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
			return ResponseEntity.of(problemDetail).build();
		}
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
		try {
			productService.deleteProduct(id);
			return ResponseEntity.noContent().build();	
		}catch(Exception e) {
			ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
			return ResponseEntity.of(problemDetail).build();
		}
	}
	
}
